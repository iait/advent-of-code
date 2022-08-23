package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Diver {

    public SubmarinePosition divePartOne(InputStream is) {

        int horizontal = 0;
        int depth = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] command = line.split(" ");
                int value = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward" -> horizontal += value;
                    case "down" -> depth += value;
                    case "up" -> depth -= value;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
        return new SubmarinePosition(horizontal, depth);
    }

    public SubmarinePosition divePartTwo(InputStream is) {

        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] command = line.split(" ");
                int value = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward" -> { horizontal += value; depth += aim * value; }
                    case "down" -> aim += value;
                    case "up" -> aim -= value;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
        return new SubmarinePosition(horizontal, depth);
    }

}
