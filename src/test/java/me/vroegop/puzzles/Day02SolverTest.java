package me.vroegop.puzzles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day02SolverTest {


    Day02Solver solver = new Day02Solver();

    @Test
    void solvePart1WithOneSimpleRange() {
        String range1 = "10-12";
        long total = solver.solvePart1(List.of(range1));
        assertEquals(11, total);
    }
}