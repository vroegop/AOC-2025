package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;
import me.vroegop.helpers.day5.Range;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day05Solver implements DaySolver<Long> {
    @Override
    public Long solvePart1(List<String> input) {
        List<Long> ids = new ArrayList<>();
        List<Range> freshIdRanges = new ArrayList<>();

        for(String s : input) {
            if (s.contains("-")) {
                freshIdRanges.add(new Range(s));
            } else if (!s.isEmpty()) {
                ids.add(Long.parseLong(s));
            }
        }

        return ids.stream()
                .filter(id -> freshIdRanges.stream()
                        .anyMatch(range -> id > range.start() && id <= range.end())
                ).count();
    }

    @Override
    public Long solvePart2(List<String> input) {
        List<Range> idRanges = input.stream()
                .filter(s -> s.contains("-"))
                .map(Range::new)
                .toList();

        boolean anyChanged = true;

        while(anyChanged) {
            anyChanged = false;
            for (Range range : idRanges) {
                for (Range range2 : idRanges) {
                    if (range.merge(range2)) {
                        anyChanged = true;
                    }
                }
            }
        }

        // Initially storing them in a set does not change the hash of the object in mutations,
        // so an initial set doesn't guarantee distinctness
        Set<Range> distinctRanges = new HashSet<>(idRanges);

        return distinctRanges.stream()
                // +1 because of inclusive start and end id's, end - start is 1 short of the right answer
                // Also we have to calculate this instead of mapping all the values because mapping
                // 369761800782619 values into a list breaks the memory :D
                .mapToLong(range -> range.end() - range.start() + 1)
                .sum();
    }
}