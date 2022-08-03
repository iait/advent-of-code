package com.adventofcode;

import java.io.*;

public class App {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/main/resources/input.txt");
        InputStream is = new FileInputStream(file);
        addListNumbers(is);
    }

    public static long addListNumbers(InputStream is) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line = br.readLine();
            if (line != null) {
                SnailfishNumber sum = SnailfishParser.parse(line);
                while ((line = br.readLine()) != null) {
                    sum = SnailfishMath.add(sum, SnailfishParser.parse(line));
                }
                System.out.println("final sum: " + sum);
                System.out.println("magnitude: " + sum.magnitude());
                return sum.magnitude();
            }
            throw new RuntimeException("There is no input");
        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
    }
}
