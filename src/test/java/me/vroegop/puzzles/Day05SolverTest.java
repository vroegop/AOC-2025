package me.vroegop.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05SolverTest {

    List<String> puzzle = List.of(
            "3-5",
                    "10-14",
                    "16-20",
                    "12-18",
                    "",
                    "1",
                    "5",
                    "8",
                    "11",
                    "17",
                    "32"
    );
    Day05Solver solver = new Day05Solver();

    @Test
    void solvePart1WithOneSimpleRange() {
        Long movableRolls = solver.solvePart1(puzzle);
        assertEquals(3, movableRolls);
    }

    @Test
    void solvePart2WithOneSimpleRange() {
        Long movableRolls = solver.solvePart2(puzzle);
        assertEquals(14, movableRolls);
    }
}