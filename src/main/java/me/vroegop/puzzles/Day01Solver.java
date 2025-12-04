package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;
import me.vroegop.helpers.day1.Rotation;

import java.util.List;

public class Day01Solver implements DaySolver<Integer> {

    @Override
    public int day() {
        return 1;
    }

    @Override
    public Integer solvePart1(List<String> input) {
        List<Rotation> rotations = input.stream()
                .filter(s -> !s.isBlank())
                .map(Rotation::new)
                .toList();

        int rotationValue = 50;
        int setToZeroTimes = 0;

        for (Rotation rotation : rotations) {
            rotationValue += rotation.toRight() ? rotation.value() : -rotation.value();
            if (rotationValue % 100 == 0) {
                setToZeroTimes++;
            }
            rotationValue %= 100;
        }

        return setToZeroTimes;
    }

    @Override
    public Integer solvePart2(List<String> input) {
        List<Rotation> rotations = input.stream()
                .filter(s -> !s.isBlank())
                .map(Rotation::new)
                .toList();

        int rotationValue = 50;
        int setToZeroTimes = 0;

        for (Rotation rotation : rotations) {
            for (int i = 0; i < rotation.value(); i++) {
                rotationValue += rotation.toRight() ? 1 : -1;
                if (rotationValue % 100 == 0) {
                    setToZeroTimes++;
                }
                rotationValue %= 100;
            }
        }

        return setToZeroTimes;
    }
}