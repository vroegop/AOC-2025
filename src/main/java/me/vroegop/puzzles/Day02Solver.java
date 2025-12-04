package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;
import me.vroegop.helpers.day2.Range;

import java.util.List;
import java.util.stream.Stream;

public class Day02Solver implements DaySolver<Long> {

    @Override
    public int day() {
        return 2;
    }

    @Override
    public Long solvePart1(List<String> input) {
        Long answer = input.stream()
                .filter(s -> !s.isBlank())
                .flatMap(allRanges -> Stream.of(allRanges.split(",")))
                .map(Range::new)
                .flatMap(Range::values)
                .map(String::valueOf)
                .filter(id -> id.length() % 2 == 0)
                .filter(id -> id.substring(0, id.length() / 2).equals(id.substring(id.length() / 2)))
                .mapToLong(Long::parseLong)
                .sum();

        return answer;
    }

    @Override
    public Long solvePart2(List<String> input) {
        Long answer = input.stream()
                .filter(s -> !s.isBlank())
                .flatMap(allRanges -> Stream.of(allRanges.split(",")))
                .map(Range::new)
                .flatMap(Range::values)
                .map(String::valueOf)
                .filter(this::isMadeOfRepeatingSequence)
                .mapToLong(Long::parseLong)
                .sum();

        return answer;
    }

    private boolean isMadeOfRepeatingSequence(String id) {
        int idLength = id.length();
        if (idLength < 2) return false;

        everyPatternLoop: for (int patternLength = 1; patternLength <= idLength / 2; patternLength++) {
            boolean canRepeatPatternBasedOnPatternLength = idLength % patternLength == 0;
            if (!canRepeatPatternBasedOnPatternLength) continue;

            boolean patternRepeats = true;
            repeatedLoopCheck: for (int i = patternLength; i < idLength; i++) {
                char charAtIndex = id.charAt(i);
                char charAtPatternIndex = id.charAt(i % patternLength);
                if (charAtIndex != charAtPatternIndex) {
                    patternRepeats = false;
                    break repeatedLoopCheck;
                }
            }

            if (patternRepeats) return true;
        }

        return false;
    }
}