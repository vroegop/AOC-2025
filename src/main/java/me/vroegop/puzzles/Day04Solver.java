package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04Solver implements DaySolver<Long> {
    @Override
    public Long solvePart1(List<String> input) {
        List<List<String>> grid = input.stream()
                .filter(s -> !s.isBlank())
                .map(row -> Arrays.stream(row.split("")).toList())
                .toList();

        int rows = grid.size();
        int cols = grid.getFirst().size();
        long movableRolls = 0;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (canMove(grid,  i, j)) {
                    movableRolls++;
                }
            }
        }

        return movableRolls;
    }

    @Override
    public Long solvePart2(List<String> input) {
        // Use collector instead of toList on the stream because the list needs to be modifiable
        List<List<String>> grid = input.stream()
                .filter(s -> !s.isBlank())
                .map(row -> Arrays.stream(row.split("")).collect(Collectors.toList()))
                .collect(Collectors.toList());

        long movedRolls = moveRolls(grid);
        long previouslyMoved = movedRolls;

        while(previouslyMoved > 0) {
            previouslyMoved = moveRolls(grid);
            movedRolls += previouslyMoved;
        }

        return movedRolls;
    }

    // move rolls that are able to move and replace them by an empty space, return the amount of moved rolls
    private static int moveRolls(List<List<String>> grid) {
        int rows = grid.size();
        int cols = grid.getFirst().size();
        int moved = 0;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (canMove(grid,  i, j)) {
                    moved++;
                    grid.get(i).set(j, ".");
                }
            }
        }

        return moved;
    }

    // A roll of paper can move if fewer than 4 rolls of paper are around it
    private static boolean canMove(List<List<String>> grid, int y, int x) {
        if (grid.get(y).get(x).equals(".")) {
            return false;
        }
        List<String> neighbors = new ArrayList<>();

        int rows = grid.size();
        int cols = grid.getFirst().size();

        // Loop through a 3×3 area around (y, x)
        for (int ny = Math.max(0, y - 1); ny <= Math.min(rows - 1, y + 1); ny++) {
            for (int nx = Math.max(0, x - 1); nx <= Math.min(cols - 1, x + 1); nx++) {

                // Skip the center cell itself
                if (ny == y && nx == x) continue;

                neighbors.add(grid.get(ny).get(nx));
            }
        }
        long rollsOfPaper = neighbors.stream().filter(item -> item.equals("@")).count();
        boolean canMove = rollsOfPaper < 4;
        return canMove;
    }
}