package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class InputParser {

    private final EnhancementAlgorithm enhancementAlgorithm;
    private final OceanImage oceanImage;

    public InputParser(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String firstLine = br.readLine();
            enhancementAlgorithm = new EnhancementAlgorithm(firstLine, 3);
            br.readLine(); // empty line
            oceanImage = OceanImage.createOceanImage(br.lines().collect(Collectors.toList()), '.');
        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
    }

    public EnhancementAlgorithm getEnhancementAlgorithm() {
        return enhancementAlgorithm;
    }

    public OceanImage getOceanImage() {
        return oceanImage;
    }
}
