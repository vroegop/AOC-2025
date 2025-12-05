package me.vroegop.helpers.day5;

import java.util.Objects;

public class Range {
    long start;
    long end;

    public Range(String input) {
        this.start = Long.parseLong(input.split("-")[0]);
        this.end = Long.parseLong(input.split("-")[1]);
    }

    public boolean merge(Range other) {
        long oldStart = this.start;
        long oldEnd = this.end;

        if (other.start <= this.start && other.end >= this.end) {
            this.start = other.start;
            this.end = other.end;
        }
        if (other.start >= this.start && other.start <= this.end && other.end >= this.end) {
            this.end = other.end;
        }
        if (other.end >= this.start && other.end <= this.end && other.start <= this.start) {
            this.start = other.start;
        }
        return oldStart != this.start && oldEnd != this.end;
    }

    public long start() {
        return start;
    }

    public long end() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start == range.start && end == range.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
