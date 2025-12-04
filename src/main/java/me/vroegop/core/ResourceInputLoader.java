package me.vroegop.core;

import com.google.inject.Singleton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ResourceInputLoader implements InputLoader {

    @Override
    public List<String> loadInput(int day) {
        String filename = String.format("/input/day%02d.txt", day);
        InputStream in = getClass().getResourceAsStream(filename);

        if (in == null) {
            throw new IllegalArgumentException("Input file not found: " + filename);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            return reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read input file: " + filename, e);
        }
    }
}