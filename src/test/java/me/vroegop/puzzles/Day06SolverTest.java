package me.vroegop.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06SolverTest {

    List<String> puzzle = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314",
            "*   +   *   +  "
    );
    List<String> puzzle2 = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "*   +   *   +  "
    );
    Day06Solver solver = new Day06Solver();

    @Test
    void solvePart1WithOneSimpleRange() {
        Long movableRolls = solver.solvePart1(puzzle);
        assertEquals(4277556, movableRolls);
    }

    @Test
    void solvePart1WithUnevenSimpleRange() {
        Long movableRolls = solver.solvePart1(puzzle2);
        assertEquals(25751, movableRolls);
    }

    @Test
    void solvePart2WithOneSimpleRange() {
        Long movableRolls = solver.solvePart2(puzzle);
        assertEquals(3263827, movableRolls);
    }

    @Test
    void solvePart2WithUnevenSimpleRange() {
        Long movableRolls = solver.solvePart2(puzzle2);
        assertEquals(3971, movableRolls);
    }
}