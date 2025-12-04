package me.vroegop.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03SolverTest {


    Day03Solver solver = new Day03Solver();

    @Test
    void solvePart1WithOneSimpleRange() {
        long total = solver.solvePart1(List.of(
                "987654321111111"
        ));
        assertEquals(98, total);
    }

    @Test
    void solvePart1WithOneSimpleRange2() {
        long total = solver.solvePart1(List.of(
                "811111111111119"
        ));
        assertEquals(89, total);
    }

    @Test
    void solvePart1WithOneSimpleRange3() {
        long total = solver.solvePart1(List.of(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
        ));
        assertEquals(357, total);
    }

    @Test
    void solvePart2WithOneSimpleRange1() {
        long total = solver.solvePart2(List.of(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
        ));
        assertEquals(3121910778619L, total);
    }
}