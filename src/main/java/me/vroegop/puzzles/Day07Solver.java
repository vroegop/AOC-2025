package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day07Solver implements DaySolver<Long> {
    @Override
    public Long solvePart1(List<String> input) {
        List<String> solution = new ArrayList<>(input);
        Long splits = 0L;

        for (int i = 0; i < solution.size(); i++) {
            if (i < solution.size() - 1) {
                String[] row = solution.get(i).split("");
                String[] next = solution.get(i + 1).split("");

                for (int j = 0; j < row.length; j++) {
                    if (row[j].equals("S") && next[j].equals(".")) {
                        next[j] = "|";
                    }
                    if (row[j].equals("|") && next[j].equals(".")) {
                        next[j] = "|";
                    }
                    if (row[j].equals("|") && next[j].equals("^")) {
                        if (!next[j - 1].equals("|")) {
                            next[j - 1] = "|";
                        }
                        next[j + 1] = "|";
                        splits++;
                    }
                }

                String collect = String.join("", next);
                solution.set(i + 1, collect);
            }
        }

        return splits;
    }

    @Override
    public Long solvePart2(List<String> input) {
        List<List<String>> solution = new ArrayList<>(input).stream()
                .map(s -> Arrays.stream(s.split("")).map(s2 -> s2.equals(".") ? "0" : s2).collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (int i = 0; i < solution.size(); i++) {
            if (i < solution.size() - 1) {
                List<String> row = solution.get(i);
                List<String> next = solution.get(i + 1);

                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).equals("S")) {
                        next.set(j, "1");
                    }
                    if (row.get(j).matches("\\d+") && next.get(j).matches("\\d+")) {
                        Long value = Long.parseLong(row.get(j)) + Long.parseLong(next.get(j));
                        next.set(j, String.valueOf(value));
                    }
                    if (row.get(j).matches("\\d+") && next.get(j).equals("^")) {
                        Long leftValue = Long.parseLong(row.get(j)) + Long.parseLong(next.get(j - 1));
                        next.set(j - 1, String.valueOf(leftValue));
                        next.set(j + 1, row.get(j));
                    }
                }

                solution.set(i + 1, next);
            }
        }

//        List<String> printableSolution = solution.stream().map(l -> String.join(" ", l)).toList();
//        printableSolution.forEach(System.out::println);

        /**
         * This is what the example tree looks like with this code:
         *
         * 0  = "0 0 0 0 0 0 0 S 0 0 0 0 0 0 0"
         * 1  = "0 0 0 0 0 0 0 1 0 0 0 0 0 0 0"
         * 2  = "0 0 0 0 0 0 1 ^ 1 0 0 0 0 0 0"
         * 3  = "0 0 0 0 0 0 1 0 1 0 0 0 0 0 0"
         * 4  = "0 0 0 0 0 1 ^ 2 ^ 1 0 0 0 0 0"
         * 5  = "0 0 0 0 0 1 0 2 0 1 0 0 0 0 0"
         * 6  = "0 0 0 0 1 ^ 3 ^ 3 ^ 1 0 0 0 0"
         * 7  = "0 0 0 0 1 0 3 0 3 0 1 0 0 0 0"
         * 8  = "0 0 0 1 ^ 4 ^ 3 3 1 ^ 1 0 0 0"
         * 9  = "0 0 0 1 0 4 0 3 3 1 0 1 0 0 0"
         * 10 = "0 0 1 ^ 5 ^ 4 3 4 ^ 2 ^ 1 0 0"
         * 11 = "0 0 1 0 5 0 4 3 4 0 2 0 1 0 0"
         * 12 = "0 1 ^ 1 5 4 ^ 7 4 0 2 1 ^ 1 0"
         * 13 = "0 1 0 1 5 4 0 7 4 0 2 1 0 1 0"
         * 14 = "1 ^ 2 ^ 10 ^ 11 ^ 11 ^ 2 1 1 ^ 1"
         * 15 = "1 0 2 0 10 0 11 0 11 0 2 1 1 0 1"
         *
         * Every split inserts the current value left and right below it, adding the previous value to it
         * Every time the number can go straight down, it adds itself to the number below it
         */

        return solution.get(solution.size() - 1).stream().mapToLong(v -> Long.parseLong(v.matches("\\d+") ? v : "0")).sum();
    }
}