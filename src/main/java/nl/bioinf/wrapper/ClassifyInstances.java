/*
 * Copyright (c) 2021. Rose Hazenberg
 * Licensed under GPLv3. See gpl.md
 *
 *
 */

package nl.bioinf.wrapper;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class ClassifyInstances {
    protected final String randomForestModelFile = "data/randomforest.model";

    protected void classifyUnknownInstances(RandomForest tree, Instances test) throws Exception {
        Instances labeled = new Instances(test);
        for (int i = 0; i < test.numInstances(); i++) {
            double label = tree.classifyInstance(test.instance(i));
            labeled.instance(i).setClassValue(label);
        }
        System.out.println("\nNew labeled = \n" + labeled);
    }

    protected RandomForest loadClassifier() throws Exception {
        return (RandomForest) weka.core.SerializationHelper.read(randomForestModelFile);
    }

    protected void saveClassifier(RandomForest randomForest) throws Exception {
        weka.core.SerializationHelper.write(randomForestModelFile, randomForest);
    }

    protected RandomForest buildTree(Instances test) throws Exception {
        RandomForest tree = new RandomForest();
        tree.buildClassifier(test);
        return tree;
    }

//    protected void printInstances(Instances instances) {
//        int numAttributes = instances.numAttributes();
//
//        for (int i = 0; i < numAttributes; i++) {
//            System.out.println("attribute " + i + " = " + instances.attribute(i));
//        }
//
//        System.out.println("class index = " + instances.classIndex());
//
//        int numInstances = instances.numInstances();
//        for (int i = 0; i < numInstances; i++) {
//            if (i == 5) break;
//            Instance instance = instances.instance(i);
//            System.out.println("instance = " + instance);
//        }
//    }
}
