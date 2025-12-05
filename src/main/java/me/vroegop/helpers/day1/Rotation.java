package me.vroegop.helpers.day1;

public record Rotation(boolean toRight, int value) {
    public Rotation(String input) {
        this(
            input.startsWith("R"),
            Integer.parseInt(input.substring(1))
        );
    }

    public int getValue() {
        return toRight ? value : -value;
    }
}