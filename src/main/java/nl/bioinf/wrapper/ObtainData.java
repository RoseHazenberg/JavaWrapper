/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import java.io.IOException;

/**
 * This class obtains the data of the arff file and remove the attribute that is necessary.
 */

public class ObtainData {
    /**
     * Is protected, so it can only be used by files of the same package.
     * This removed the 10 attribute from the unknown arff file which isn't needed.
     * @param datafile
     * @return filteredData
     * @throws Exception
     */
    protected Instances removeAttribute(Instances datafile) throws Exception {
        Remove remove = new Remove();
        remove.setAttributeIndices(String.valueOf(10));
        remove.setInvertSelection(false);
        remove.setInputFormat(datafile);
        Instances filteredData = Filter.useFilter(datafile, remove);
        return filteredData;
    }

    /**
     * Is protected, so it can only be used in files from the same package.
     * Reads both files and load the instances.
     * @param datafile
     * @return data
     * @throws IOException
     */
    protected Instances loadFromArffFile(String datafile) throws IOException {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1);
            data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("[Error] Failed to load instances from file " + datafile);
        }
    }
}
