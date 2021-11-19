/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 * Public class which classifies the unknown classes of the instances.
 * Everything is protected ,so it can be used with the class in the package.
 */

public class ClassifyInstances {
    /**
     * String of model file name which is final, cannot be altered.
     * Is protected which can be used throughout the package.
     */
    protected final String randomForestModelFile = "data/randomforest.model";

    /**
     * Classifies the new instances with the RandomForest tree.
     * @param tree RandomForest tree which is build
     * @param test unknown instances
     * @throws Exception when it doesn't work
     */
    protected void classifyUnknownInstances(RandomForest tree, Instances test) throws Exception {
        Instances predicted = new Instances(test);
        for (int i = 0; i < test.numInstances(); i++) {
            double pred = tree.classifyInstance(test.instance(i));
            predicted.instance(i).setClassValue(pred);
            System.out.println("ID: " + test.instance(i).value(0) + ", predicted: "
                    + test.classAttribute().value((int) pred));
        }
    }

    /**
     * Reads the classifier from the model file.
     * @return RandomForest model
     * @throws Exception is failed to load
     */
    protected RandomForest loadClassifier() throws Exception {
        return (RandomForest) weka.core.SerializationHelper.read(randomForestModelFile);
    }

    /**
     * Writes the classifier with model file.
     * @param randomForest RandomForest tree
     * @throws Exception if something went wrong
     */
    protected void saveClassifier(RandomForest randomForest) throws Exception {
        weka.core.SerializationHelper.write(randomForestModelFile, randomForest);
    }

    /**
     * Build the RandomForest tree.
     * @param test the arff file with known classes
     * @return build tree
     * @throws Exception if the building doesn't work
     */
    protected RandomForest buildTree(Instances test) throws Exception {
        RandomForest tree = new RandomForest();
        tree.buildClassifier(test);
        return tree;
    }
}
