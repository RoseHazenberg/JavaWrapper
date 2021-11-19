/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

/**
 * Interface that give the options that is requested.
 */

public interface GiveOptions {
    /**
     * Gives the name of the arff file
     * @return arff file
     */
    String getFileName();

    /**
     * Give the name of the arff file with the unknown class of the instances
     * @return arff file
     */
    String getUnknownFile();
}
