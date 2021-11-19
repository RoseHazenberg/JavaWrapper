/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

public class ArgsGetter {
    void activation(GiveOptions giveOptions) {
        String knownInputFile = giveOptions.getFileName();
        System.out.println("inputFile: " + knownInputFile);
        String unknownInputFile = giveOptions.getUnknownFile();
        System.out.println("unknownInputFile = " + unknownInputFile);
    }
}
