package me.vroegop.puzzles;

import me.vroegop.core.DaySolver;

import java.util.ArrayList;
import java.util.List;

public class Day06Solver implements DaySolver<Long> {
    @Override
    public Long solvePart1(List<String> input) {
        List<List<String>> calculations = new ArrayList<>();
        List<Long> answers = new ArrayList<>();

        int rows = input.size();
        int columns = splitByWhitespaces(input.get(0).trim()).length;
        for (int i = 0; i < rows; i++) {
            calculations.add(new ArrayList<>());
        }
        for (int i = 0; i < columns; i++) {
            answers.add(0L);
        }

        // For easy debugging, first collect the calculations in readable datasets
        // I could do without for optimizing memory but this is more stable
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String[] strings = splitByWhitespaces(input.get(i).trim());
                calculations.get(i).add(strings[j]);
            }
        }

        // Calculate the answers
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                boolean multiply = calculations.getLast().get(j).equals("*");

                if (multiply) {
                    if (answers.get(j) == 0) answers.set(j, Long.parseLong(calculations.get(i).get(j)));
                    else answers.set(j, answers.get(j) * Long.parseLong(calculations.get(i).get(j)));
                } else {
                    answers.set(j, answers.get(j) + Long.parseLong(calculations.get(i).get(j)));
                }
            }
        }

        // Sum all answers
        return answers.stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public Long solvePart2(List<String> input) {
        List<String> calculations = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                String character = input.get(i).substring(j, j + 1);
                if (calculations.size() <= j) calculations.add(character.equals(" ") ? " " : character);
                else if (!character.equals(" ")) calculations.set(j, calculations.get(j) + character);
                else if (character.equals(" ")) calculations.set(j, calculations.get(j) + " ");
            }
        }

        /**
         * We now have an array with all the right structures:
         * 0 = "1  *"
         * 1 = "24  "
         * 2 = "356 "
         * 3 = "    "
         * etc.
         *
         * This means we can multiply or add the values if we find * or + in the first sequence, and trim the rest
         * until we find an empty string which means we have to go to the next answer.
         */

        List<Long> answers = new ArrayList<>();
        int calculatingAnswerN = 0;
        boolean multiply = true;

        for (int i = 0; i < calculations.size(); i++) {
            if (calculations.get(i).contains("+")) {
                multiply = false;
            }
            if (calculations.get(i).contains("*")) {
                multiply = true;
            }
            if (calculations.get(i).trim().equals("")) {
                calculatingAnswerN++;
                continue;
            }

            // Remove the last character (this can be + or * or a whitespace
            // Then trim to get the actual value
            String calculationValue = calculations.get(i).substring(0, calculations.get(i).length() - 1).trim();
            if (answers.size() < calculatingAnswerN + 1) {
                answers.add(Long.parseLong(calculationValue));
                continue;
            }
            if (multiply) {
                answers.set(calculatingAnswerN, answers.get(calculatingAnswerN) * Long.parseLong(calculationValue));
            } else {
                answers.set(calculatingAnswerN, answers.get(calculatingAnswerN) + Long.parseLong(calculationValue));
            }
        }


        return answers.stream().mapToLong(Long::longValue).sum();
    }

    private String[] splitByWhitespaces(String input) {
        return input.split("\\s+");
    }
}