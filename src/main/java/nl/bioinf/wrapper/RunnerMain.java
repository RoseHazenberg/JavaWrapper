/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

import weka.core.Instances;
import weka.classifiers.trees.RandomForest;

/**
 * This class is the main which gets the arguments and classifies the new instances. Set everything to work.
 */

public class RunnerMain {
    /**
     * @param args from the command line
     */
    public static void main(String[] args) {
        GiveOptions giveOptions = new GiveOptionsGetter(args);
        ArgsGetter argsGetter = new ArgsGetter();
        argsGetter.activation(giveOptions);
        ClassifyInstances classifyInstances = new ClassifyInstances();
        ObtainData getData = new ObtainData();
        try {
            Instances train = getData.loadFromArffFile(giveOptions.getFileName());
            RandomForest randomForest = classifyInstances.buildTree(train);
            classifyInstances.saveClassifier(randomForest);
            RandomForest fromFile = classifyInstances.loadClassifier();
            Instances test = getData.loadFromArffFile(giveOptions.getUnknownFile());
            Instances removedAttribute = getData.removeAttribute(test);
            classifyInstances.classifyUnknownInstances(fromFile, removedAttribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
