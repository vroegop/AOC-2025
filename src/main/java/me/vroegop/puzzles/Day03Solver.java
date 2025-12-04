package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day03Solver implements DaySolver<Long> {

    @Override
    public int day() {
        return 3;
    }

    @Override
    public Long solvePart1(List<String> input) {
        long answer = input.stream()
                .filter(s -> !s.isBlank())
                .mapToLong(batteries -> largestNumberPossibleWithNBatteries(2, batteries))
                .sum();

        return answer;
    }

    @Override
    public Long solvePart2(List<String> input) {
        long answer = input.stream()
                .filter(s -> !s.isBlank())
                .mapToLong(batteries -> largestNumberPossibleWithNBatteries(12, batteries))
                .sum();

        return answer;
    }

    private long largestNumberPossibleWithNBatteries(int batteries, String jolts) {
        List<Long> values = new ArrayList<>(Collections.nCopies(batteries, 0L));

        List<Long> characters = Arrays.stream(jolts.split("")).map(Long::parseLong).toList();
        
        everyBatteryLoop: for (int i = 0; i < characters.size(); i++) {
            chosenTwelveBatteryLoop: for(int j = 0; j < batteries; j++) {
                if ((characters.size() - 1) - i >= (batteries - 1) - j) {
                    if (values.get(j) < characters.get(i)) {
                        values.set(j, characters.get(i));

                        for (int k = j + 1; k < batteries; k++) {
                            values.set(k, 0L);
                        }

                        break chosenTwelveBatteryLoop;
                    }
                }
            }
        }

        return Long.parseLong(values.stream().map(String::valueOf).collect(Collectors.joining()));
    }
}