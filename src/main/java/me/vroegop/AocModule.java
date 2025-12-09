package me.vroegop;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import me.vroegop.core.DaySolver;
import me.vroegop.core.InputLoader;
import me.vroegop.core.ResourceInputLoader;
import me.vroegop.puzzles.*;

import java.util.List;

public class AocModule extends AbstractModule {

    private static final List<DaySolver> SOLVERS = List.of(
            new Day01Solver(),
            new Day02Solver(),
            new Day03Solver(),
            new Day04Solver(),
            new Day05Solver(),
            new Day06Solver(),
            new Day07Solver(),
            new Day08Solver()
    );

    @Override
    protected void configure() {
        // Input loader
        bind(InputLoader.class).to(ResourceInputLoader.class);

        // Bind all solvers for this year
        MapBinder<Integer, DaySolver> dayBinder = MapBinder.newMapBinder(binder(), Integer.class, DaySolver.class);

        for (int i = 0; i < SOLVERS.size(); i++) {
            dayBinder.addBinding(i + 1).toInstance(SOLVERS.get(i));
        }
    }
}