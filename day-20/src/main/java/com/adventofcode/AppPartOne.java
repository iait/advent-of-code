package com.adventofcode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AppPartOne {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Advent of code: Day 20");
        System.out.println("Part One");

        InputStream is = new FileInputStream("src/main/resources/input.txt");
        InputParser parser = new InputParser(is);
        var enhancementAlgorithm = parser.getEnhancementAlgorithm();
        var oceanImage = parser.getOceanImage();
        oceanImage = oceanImage.applyEnhancement(enhancementAlgorithm);
        oceanImage = oceanImage.applyEnhancement(enhancementAlgorithm);
        System.out.println("Result: " + oceanImage.lightCount());
    }
}
