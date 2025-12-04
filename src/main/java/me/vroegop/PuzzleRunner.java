package me.vroegop;

import com.google.inject.Inject;
import me.vroegop.core.DaySolver;
import me.vroegop.core.InputLoader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PuzzleRunner {

    private final InputLoader inputLoader;
    private final Map<Integer, DaySolver> solvers;

    @Inject
    public PuzzleRunner(InputLoader inputLoader,
                        Map<Integer, DaySolver> solvers) {
        this.inputLoader = inputLoader;
        this.solvers = solvers;
    }

    public void run(int day, int part) {
        DaySolver solver = Optional.ofNullable(solvers.get(day))
                .orElseThrow(() -> new IllegalArgumentException("No solver registered for day " + day));

        List<String> input = inputLoader.loadInput(day);

        Object answer;
        switch (part) {
            case 1 -> answer = solver.solvePart1(input);
            case 2 -> answer = solver.solvePart2(input);
            default -> throw new IllegalArgumentException("Invalid part: " + part);
        }

        System.out.printf("Day %02d Part %d => %s%n", day, part, answer);
    }

    public void run() {
        solvers.forEach((day, solver) -> {
            run(day, 1);
            run(day, 2);
        });
    }
}