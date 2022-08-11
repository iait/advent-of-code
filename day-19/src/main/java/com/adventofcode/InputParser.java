package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.adventofcode.App.*;
import static java.lang.Integer.parseInt;

public class InputParser {

    public static final String PREFIX = "--- scanner ";
    public static final String SUFFIX = " ---";
    public static final String DELIMITER = ",";

    public static List<Scanner> parse(InputStream is) {

        List<Scanner> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line = br.readLine();
            if (line != null) {
                Scanner scanner = null;
                do {
                    if (line.isEmpty()) {
                        continue;
                    }
                    if (line.startsWith(PREFIX)) {
                        String name = line.substring(PREFIX.length(), line.lastIndexOf(SUFFIX));
                        scanner = new Scanner(parseInt(name), new ArrayList<>());
                        input.add(scanner);
                    } else if (scanner != null) {
                        String[] components = line.split(DELIMITER);
                        scanner.beacons().add(new Point(
                                parseInt(components[0]),
                                parseInt(components[1]),
                                parseInt(components[2])));
                    } else {
                        throw new RuntimeException("Missing scanner tag");
                    }

                } while ((line = br.readLine()) != null);
            }
            return input;
        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
    }

}
