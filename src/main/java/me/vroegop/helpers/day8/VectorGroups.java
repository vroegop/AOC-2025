package me.vroegop.helpers.day8;

import java.util.*;

public record VectorGroups(List<Set<Vector3D>> vector3DList) {
    public static Vector3D lastAdded;
    public static Vector3D oneToLastAdded;

    public VectorGroups() {
        this(new ArrayList<>());
    }

    public boolean add(VectorPair pair) {
        Vector3D a = pair.a();
        Vector3D b = pair.b();

        Optional<Set<Vector3D>> duplicate = vector3DList.stream().filter(v -> v.contains(a) && v.contains(b)).findFirst();

        if (duplicate.isPresent()) {
            return true;
        }

        Optional<Set<Vector3D>> isAlreadyInSetForA = vector3DList.stream().filter(v -> v.contains(a)).findFirst();
        Optional<Set<Vector3D>> isAlreadyInSetForB = vector3DList.stream().filter(v -> v.contains(b)).findFirst();

        if (isAlreadyInSetForA.isPresent() && isAlreadyInSetForB.isPresent()) {
            isAlreadyInSetForB.get().addAll(isAlreadyInSetForA.get());
            vector3DList.remove(isAlreadyInSetForA.get());
            lastAdded = a;
            oneToLastAdded = b;
            return true;
        }

        Optional<Set<Vector3D>> first = vector3DList.stream().filter(v -> v.contains(a) || v.contains(b)).findFirst();

        if (first.isPresent()) {
            first.get().add(a);
            first.get().add(b);
            lastAdded = a;
            oneToLastAdded = b;
        } else {
            HashSet<Vector3D> newSet = new HashSet<>();
            newSet.add(a);
            newSet.add(b);
            lastAdded = a;
            oneToLastAdded = b;
            vector3DList.add(newSet);
        }

        // check if A and B are already present in different sets


        return true;
    }
}
