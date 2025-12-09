package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;
import me.vroegop.helpers.day8.Vector3D;
import me.vroegop.helpers.day8.VectorGroups;
import me.vroegop.helpers.day8.VectorPair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day08Solver implements DaySolver<Long> {
    @Override
    public Long solvePart1(List<String> input) {
        List<Vector3D> vectors = input.stream().map(Vector3D::new).toList();

        List<VectorPair> closest = closestPairs(vectors);

        VectorGroups vectorGroups = new VectorGroups();

        int n = 0;
        while (n < 1000) {
            // If both nodes are already part of the same circuit it does not add
            if (closest.isEmpty()) {
                n = 1000;
                continue;
            }
            boolean added = vectorGroups.add(closest.removeFirst());
            if (added) {
                n++;
            }
        }

        long distances = vectorGroups.vector3DList().stream().map(Set::size).sorted(Comparator.reverseOrder()).limit(3).reduce(0, (a, b) -> a > 0 ? a * b : b);
        return distances;
    }

    @Override
    public Long solvePart2(List<String> input) {
        List<Vector3D> vectors = input.stream().map(Vector3D::new).toList();

        List<VectorPair> closest = closestPairs(vectors);

        VectorGroups vectorGroups = new VectorGroups();
        vectorGroups.add(closest.removeFirst());

        while (vectorGroups.vector3DList().get(0).size() < 1000) {
            vectorGroups.add(closest.removeFirst());
        }

        return VectorGroups.lastAdded.x() * VectorGroups.oneToLastAdded.x();
    }


    public List<VectorPair> closestPairs(List<Vector3D> vectors) {
        int n = vectors.size();
        List<VectorPair> pairs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {  // j = i+1 avoids duplicates and self-pairs
                Vector3D v1 = vectors.get(i);
                Vector3D v2 = vectors.get(j);
                double dist = v1.straightLineDistance(v2);
                pairs.add(new VectorPair(v1, v2, dist));
            }
        }

        return pairs.stream()
                .sorted(Comparator.comparingDouble(VectorPair::distance))
                .collect(Collectors.toList());
    }
}