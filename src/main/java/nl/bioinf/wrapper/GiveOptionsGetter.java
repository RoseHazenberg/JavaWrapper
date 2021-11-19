/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

public class GiveOptionsGetter implements GiveOptions {
    private Options options;
    private CommandLine cmd;
    private String arffFile;
    private String unknownFile;

    public GiveOptionsGetter(String [] args) {
        createOptions();
        parseCommandArgs(args);
   }

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

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        // TODO name file
        formatter.printHelp("java -jar .jar [options]", options);
    }

    @Override
    public String getFileName() {
        return arffFile;
    }

    @Override
    public String getUnknownFile() {
        return unknownFile;
    }
}
