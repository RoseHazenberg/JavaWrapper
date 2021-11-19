/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

/**
 * This class adds the options which needed to be fetched from the command line.
 * Fetch for the command line and check if the options are indeed provided.
 */

public class GiveOptionsGetter implements GiveOptions {
    private Options options;
    private CommandLine cmd;
    private String arffFile;
    private String unknownFile;

    /**
     * Create and parse the arguments.
     * @param args from the command line
     */
    public GiveOptionsGetter(String [] args) {
        createOptions();
        parseCommandArgs(args);
   }

    /**
     * Private and doesn't return anything.
     * Create the options to get the arff file with the known classes and
     * to get the file with the unknown classes. And it creates a help option.
     */
    private void createOptions() {
        try {
            this.options = new Options();
            options.addOption(new Option("f",
                    "file",
                    true,
                    "The input file with the known classes of the instances"));
            options.addOption(new Option("u",
                    "unknown",
                    true,
                    "The input file of the unknown classes of the instances"));
            options.addOption(new Option("h",
                    "help",
                    false,
                    "Prints the help for the command line arguments"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the arguments from the command and otherwise print help.
     * @param args command line args
     */
    private void parseCommandArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            this.cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                printHelp();
            }
            checkOptions();
        } catch (ParseException exp) {
            System.err.println("Parsing failed! Because something went wrong: " +
                    exp.getMessage());
            printHelp();
        }
    }

    /**
     * Checks if the options are provides.
     * @throws ParseException if not files provided
     */
   private void checkOptions() throws ParseException {
        if (cmd.hasOption('f')) {
            this.arffFile = cmd.getOptionValue('f');
        } else {
            throw new ParseException("No arff file is provided");
        }
       if (cmd.hasOption('u')) {
           this.unknownFile = cmd.getOptionValue('u');
       } else {
           throw new ParseException("No arff file is provided with unknown instances");
       }
   }

    /**
     * Creates the help if prints it is necessary in the parseCommandArgs.
     */
    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar Wrapper-1.0-SNAPSHOT-all.jar [options]", options);
    }

    /**
     * Override method for getFileName.
     * @return arffFile
     */
    @Override
    public String getFileName() {
        return arffFile;
    }

    /**
     * Override method for getUnknownFile
     * @return unknownFile
     */
    @Override
    public String getUnknownFile() {
        return unknownFile;
    }
}
