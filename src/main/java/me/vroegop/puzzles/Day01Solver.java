package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;
import me.vroegop.helpers.day1.Rotation;

import java.util.List;

public class Day01Solver implements DaySolver<Long> {
    @Override
    public Long solvePart1(List<String> input) {
        List<Integer> rotations = input.stream()
                .filter(s -> !s.isBlank())
                .map(Rotation::new)
                .map(Rotation::getValue)
                .toList();

        int rotationValue = 50;
        Long setToZeroTimes = 0L;

        for (Integer rotation : rotations) {
            rotationValue += rotation;
            rotationValue %= 100;
            if (rotationValue == 0) setToZeroTimes++;
        }

        return setToZeroTimes;
    }

    @Override
    public Long solvePart2(List<String> input) {
        List<Rotation> rotations = input.stream()
                .filter(s -> !s.isBlank())
                .map(Rotation::new)
                .toList();

        int rotationValue = 50;
        Long setToZeroTimes = 0L;

        for (Rotation rotation : rotations) {
            for (int i = 0; i < rotation.value(); i++) {
                rotationValue += rotation.toRight() ? 1 : -1;
                rotationValue %= 100;
                if (rotationValue == 0) setToZeroTimes++;
            }
        }

        return setToZeroTimes;
    }
}