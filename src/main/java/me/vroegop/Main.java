package me.vroegop;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        AocModule aocModule = new AocModule();
        Injector injector = Guice.createInjector(aocModule);
        PuzzleRunner runner = injector.getInstance(PuzzleRunner.class);

        if (args.length < 2) {
            System.err.println("Usage: java -jar aoc.jar <day> <part>");
            System.err.println("Example: java -jar aoc.jar 1 2");
            System.err.println("Now running all puzzles");
            runner.run();
        } else {
            int day = Integer.parseInt(args[0]);
            int part = Integer.parseInt(args[1]);

            runner.run(day, part);
        }
    }
}