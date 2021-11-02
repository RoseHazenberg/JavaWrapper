package nl.bioinf.wrapper;

import org.apache.commons.cli.*;

public class CliOptionsProvider implements OptionsProvider {
    private Options options;
    private CommandLine cmd;
    private String file;
    private int number;

    public CliOptionsProvider(String [] args) {
       init();
       parseArgs(args);
   }

    private void parseArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
//        CommandLine cmd = parser.parse(options, args);
        try {
            this.cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                printHelp();
            }
            verify();
        } catch (ParseException e) {
            System.err.println("Something went wrong while parsing, cause: " +
                    e.getCause());
            printHelp();
        }
    }

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -j MyApp.jar [options]", options);
    }

    private void init() {
       this.options = new Options();
//       final Options options = new Options();
       options.addOption(new Option("n",
               "number",
               true,
               "The number - a positive number"));
       options.addOption(new Option("f",
               "file",
               true,
               "The input file - expects three numeric columns"));
       options.addOption(new Option("h",
               "help",
               false,
               "Prints the help"));
   }

   private void verify() throws ParseException {
        if (!cmd.hasOption('f')) {
            throw new ParseException("no file provided");
        } else {
            this.file = cmd.getOptionValue('f');
        }
        if (!cmd.hasOption('n')) {
            throw new ParseException("no number provided");
        } else {
            try {
                final String numberStr = cmd.getOptionValue('n');
                int number = Integer.parseInt(numberStr);
                if (number < 0) {
                  throw new ParseException("Number is below zero: " + number);
                }
                this.number = number;
            } catch (NumberFormatException nfe) {
                throw new ParseException("Number cannot be parsed: " +
                        cmd.getOptionValue('n'));
            }
        }
   }
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getFileName() {
        return file;
    }
}
