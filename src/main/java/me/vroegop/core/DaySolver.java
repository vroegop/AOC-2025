package me.vroegop.core;

import java.util.List;

public interface DaySolver<T> {
    int day(); // for info/logging if you want

    T solvePart1(List<String> input);

    T solvePart2(List<String> input);
}