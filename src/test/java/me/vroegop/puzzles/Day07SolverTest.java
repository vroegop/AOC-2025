package me.vroegop.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07SolverTest {

    List<String> puzzle = List.of(
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
            "...............",
            "....^.^...^....",
            "...............",
            "...^.^...^.^...",
            "...............",
            "..^...^.....^..",
            "...............",
            ".^.^.^.^.^...^.",
            "..............."
    );

    List<String> puzzle2 = List.of(
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "...............",
            "..............."
    );

    List<String> puzzle3 = List.of(
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "..............."
    );
    Day07Solver solver = new Day07Solver();

    @Test
    void solvePart1WithOneSimpleRange() {
        Long movableRolls = solver.solvePart1(puzzle);
        assertEquals(21, movableRolls);
    }

    @Test
    void solvePart2WithOneSimplerRange() {
        Long movableRolls = solver.solvePart2(puzzle2);
        assertEquals(2, movableRolls);
    }

    @Test
    void solvePart3WithOneSimplerRange() {
        Long movableRolls = solver.solvePart2(puzzle3);
        assertEquals(10, movableRolls);
    }

    @Test
    void solvePart2WithOneSimpleRange() {
        Long movableRolls = solver.solvePart2(puzzle);
        assertEquals(40, movableRolls);
    }
}