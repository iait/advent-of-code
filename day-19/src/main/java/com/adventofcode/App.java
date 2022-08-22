package com.adventofcode;

import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class App {

    enum ProblemPart {
        ONE,
        TWO;
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Advent of code: Day 19");

        Options options = new Options();

        Option problemPartOpt = new Option("p", "part", true, "Optional: problem part");
        problemPartOpt.setRequired(false);
        options.addOption(problemPartOpt);

        Option inputFileOpt = new Option("i", "input", true, "Optional: input file");
        inputFileOpt.setRequired(false);
        options.addOption(inputFileOpt);

        Option verboseOpt = new Option("v", "verbose", false, "Optional: print details");
        inputFileOpt.setRequired(false);
        options.addOption(verboseOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        final CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp("day-19", options);
            System.exit(1);
            return;
        }

        String problemPartStr = Optional.ofNullable(cmd.getOptionValue("p"))
                .orElse("two");
        String inputFilePath = Optional.ofNullable(cmd.getOptionValue("i"))
                .orElse("src/main/resources/input.txt");
        boolean verbose = cmd.hasOption("v");

        ProblemPart problemPart = switch (problemPartStr) {
            case "one", "ONE" -> ProblemPart.ONE;
            case "two", "TWO" -> ProblemPart.TWO;
            default -> throw new IllegalArgumentException("Problem part must be 'one' or 'two'");
        };

        InputStream is = new FileInputStream(inputFilePath);
        List<Scanner> scanners = InputParser.parse(is);

        OceanMapBuilder oceanMapBuilder = new OceanMapBuilder();
        Set<Point> beacons = oceanMapBuilder.buildMap(scanners);
        if (verbose) {
            beacons.forEach(System.out::println);
        }
        System.out.println("Total number of beacons: " + beacons.size());

        if (problemPart == ProblemPart.TWO) {
            long distance = oceanMapBuilder.largestManhattanDistance(scanners);
            System.out.println("Largest Manhattan distance between scanners:" + distance);
        }
    }
}
