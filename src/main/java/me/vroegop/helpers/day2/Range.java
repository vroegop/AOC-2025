package me.vroegop.helpers.day2;

import java.util.stream.Stream;

public record Range(long start, long end) {
    public Range(String input) {
        this(
            Long.parseLong(input.split("-")[0]),
            Long.parseLong(input.split("-")[1])
        );
    }

    // 10-12 becomes a stream of 10, 11, 12
    public Stream<Long> values() {
        return Stream.iterate(start, i -> i <= end, i -> i + 1);
    }
}
