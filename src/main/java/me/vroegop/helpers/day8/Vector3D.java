package me.vroegop.helpers.day8;

import java.util.Objects;

public record Vector3D(long x, long y, long z) {
    public Vector3D(String location) {
        this(
                Long.parseLong(location.split(",")[0]),
                Long.parseLong(location.split(",")[1]),
                Long.parseLong(location.split(",")[2])
        );
    }

    public double straightLineDistance(Vector3D other) {
        return Math.sqrt(squaredDistanceTo(other));
    }

    public long squaredDistanceTo(Vector3D other) {
        long dx = other.x - x;
        long dy = other.y - y;
        long dz = other.z - z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return x == vector3D.x && y == vector3D.y && z == vector3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
