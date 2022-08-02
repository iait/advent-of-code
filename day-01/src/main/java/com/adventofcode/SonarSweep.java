package com.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class SonarSweep {

    public static int countIncreases(InputStream is) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            int count = 0;
            String line = br.readLine();
            if (line != null) {
                long last = Long.parseLong(line);
                while ((line = br.readLine()) != null) {
                    long current = Long.parseLong(line);
                    if (current > last) {
                        count++;
                    }
                    last = current;
                }
            }
            return count;
        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
    }

    public static int countWindowIncreases(InputStream is, int windowSize) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            int count = 0;
            LinkedList<Long> window = new LinkedList<>();
            String line;
            for (int i = 0; i < windowSize && (line = br.readLine()) != null; i++) {
                window.add(Long.parseLong(line));
            }
            while ((line = br.readLine()) != null) {
                long last = window.remove();
                long current = Long.parseLong(line);
                if (current > last) {
                    count++;
                }
                window.add(current);
            }
            return count;
        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
    }

}
