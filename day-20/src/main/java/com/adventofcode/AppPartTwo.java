package com.adventofcode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AppPartTwo {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Advent of code: Day 20");
        System.out.println("Part Two");

        InputStream is = new FileInputStream("src/main/resources/input.txt");
        InputParser parser = new InputParser(is);
        var enhancementAlgorithm = parser.getEnhancementAlgorithm();
        var oceanImage = parser.getOceanImage();
        System.out.println("Original image size: " + oceanImage.getSize());

        long start = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            oceanImage = oceanImage.applyEnhancement(enhancementAlgorithm);
        }
        long end = System.nanoTime();

        System.out.println("Result: " + oceanImage.lightCount());
        System.out.println("Time in ms: " + (end-start)/1_000_000);
        System.out.println("Final image size: " + oceanImage.getSize());
    }
}
