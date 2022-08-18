package com.adventofcode;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) throws FileNotFoundException {

        Options options = new Options();

        Option problemPartOpt =
                new Option("p", "part", true, "Mandatory: problem part 'one' or two'");
        problemPartOpt.setRequired(true);
        options.addOption(problemPartOpt);

        Option inputFileOpt =
                new Option("i", "input", true, "Optional: input file");
        inputFileOpt.setRequired(false);
        options.addOption(inputFileOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        final CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp("day-18", options);
            System.exit(1);
            return;
        }

        String problemPart = cmd.getOptionValue("p");
        String inputFilePath = Optional.ofNullable(cmd.getOptionValue("i"))
                .orElse("src/main/resources/input.txt");

        InputStream is = new FileInputStream(inputFilePath);
        List<SnailfishNumber> numbers = parseInput(is);
        switch (problemPart) {
            case "one", "ONE" -> addListNumbers(numbers);
            case "two", "TWO" -> findLargestMagnitude(numbers);
        }
    }

    public static List<SnailfishNumber> parseInput(InputStream is) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            List<SnailfishNumber> numbers = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(SnailfishParser.parse(line));
            }
            return numbers;
        } catch (IOException e) {
            throw new RuntimeException("Error closing resources", e);
        }
    }

    public static void addListNumbers(List<SnailfishNumber> numbers) {

        SnailfishNumber sum = numbers.stream().reduce(SnailfishMath::add)
                .orElseThrow(() -> new RuntimeException("There is no input"));
        System.out.println("final sum: " + sum);
        System.out.println("magnitude: " + sum.magnitude());
    }

    public static void findLargestMagnitude(List<SnailfishNumber> numbers) {

        long largest = 0;
        SnailfishNumber sum = null;
        SnailfishNumber[] operands = new SnailfishNumber[2];
        for (int i = 0; i < numbers.size(); i++) {
            SnailfishNumber left = numbers.get(i);
            for (int j = 0; j < numbers.size(); j++) {
                if (i == j) {
                    continue;
                }
                SnailfishNumber right = numbers.get(j);
                SnailfishNumber result = SnailfishMath.add(left, right);
                long magnitude = result.magnitude();
                if (magnitude > largest) {
                    largest = magnitude;
                    sum = result;
                    operands[0] = left;
                    operands[1] = right;
                }
            }
        }
        if (sum != null) {
            System.out.println("sum: " + operands[0] + " + " + operands[1]);
            System.out.println("result: " + sum);
            System.out.println("magnitude: " + largest);
        }
    }
}
