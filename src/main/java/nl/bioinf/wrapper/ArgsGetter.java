/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

/**
 * This class implements the interface GiveOptions where it gets the file names and prints it.
 */

public class ArgsGetter {
    /**
     * Implements the interface.
     * Get the names and prints it.
     * @param giveOptions the interface
     */
    void activation(GiveOptions giveOptions) {
        String knownInputFile = giveOptions.getFileName();
        System.out.println("inputFile: " + knownInputFile);
        String unknownInputFile = giveOptions.getUnknownFile();
        System.out.println("unknownInputFile = " + unknownInputFile);
    }
}
