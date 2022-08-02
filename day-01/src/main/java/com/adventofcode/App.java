package com.adventofcode;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

public class App {

    public static void main(String[] args) throws FileNotFoundException {

        Options options = new Options();

        Option problemPartOpt = new Option("p", "part", true, "Mandatory: problem part 'one' or two'");
        problemPartOpt.setRequired(true);
        options.addOption(problemPartOpt);

        Option inputFileOpt = new Option("i", "input", true, "Optional: input file");
        inputFileOpt.setRequired(false);
        options.addOption(inputFileOpt);

        Option windowSizeOpt = new Option("s", "size", true, "Optional: sliding window size. Default: 1");
        windowSizeOpt.setRequired(false);
        options.addOption(windowSizeOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        final CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp("day-01", options);
            System.exit(1);
            return;
        }

        String problemPart = cmd.getOptionValue("p");
        String inputFilePath = Optional.ofNullable(cmd.getOptionValue("i")).orElse("src/main/resources/input.txt");
        Integer windowSize = Optional.ofNullable(cmd.getOptionValue("s")).map(Integer::parseInt).orElse(1);

        File file = new File(inputFilePath);
        InputStream is = new FileInputStream(file);
        final int count;
        switch (problemPart) {
            case "one", "ONE" -> count = SonarSweep.countIncreases(is);
            case "two", "TWO" -> count = SonarSweep.countWindowIncreases(is, windowSize);
            default -> throw new IllegalArgumentException("Problem part must be 'one' or 'two'");
        }
        System.out.println("Count: " + count);
    }
}
