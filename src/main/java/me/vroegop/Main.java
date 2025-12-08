package me.vroegop;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AocModule aocModule = new AocModule();
        Injector injector = Guice.createInjector(aocModule);
        PuzzleRunner runner = injector.getInstance(PuzzleRunner.class);

        if (args.length < 1) {
            runner.run();
        } else {
            // ask user for two integer inputs
            Scanner sc = new Scanner(System.in);
            System.out.print("Day: ");
            int day = sc.nextInt();
            System.out.print("Part: ");
            int part = sc.nextInt();

            runner.run(day, part);
        }
    }
}