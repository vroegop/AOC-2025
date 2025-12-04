package me.vroegop.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04SolverTest {

    List<String> puzzle = List.of(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@."
    );
    Day04Solver solver = new Day04Solver();

    @Test
    void solvePart1WithOneSimpleRange() {
        Long movableRolls = solver.solvePart1(puzzle);
        assertEquals(13, movableRolls);
    }

    @Test
    void solvePart2WithOneSimpleRange() {
        Long movableRolls = solver.solvePart2(puzzle);
        assertEquals(43, movableRolls);
    }
}