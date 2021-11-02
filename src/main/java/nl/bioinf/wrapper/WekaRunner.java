package nl.bioinf.wrapper;

import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import javax.management.relation.RelationNotFoundException;
import javax.xml.crypto.Data;
import java.io.IOException;

public class WekaRunner {
    private final String modelFile = "data/randomforest.model";

    public static void main(String[] args) {
        WekaRunner runner = new WekaRunner();
        runner.start();
    }

    private void start() {
        String datafile = "data/breastcancer.arff";
        String unknownFile = "data/unknownbreastcancer.arff";
        try {
            Instances instances = loadArff(datafile);
            printInstances(instances);
            RandomForest randomForest = buildClassifier(instances);
            saveClassifier(randomForest);
            RandomForest fromFile = loadClassifier();
            Instances unknownInstances = loadArff(unknownFile);
            System.out.println("unclassified unknownInstances = \n" + unknownInstances);
            clasifyNewInstance(fromFile, unknownInstances);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clasifyNewInstance(RandomForest tree, Instances unknownInstances) throws Exception {
        Instances labeled = new Instances(unknownInstances);

        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double clslabel = tree.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(clslabel);
        }
        System.out.println("\nNew labeled = \n" + labeled);
    }

    private RandomForest loadClassifier() throws Exception {
        return (RandomForest) weka.core.SerializationHelper.read(modelFile);
    }

    private void saveClassifier(RandomForest randomForest) throws Exception {
        weka.core.SerializationHelper.write(modelFile, randomForest);
    }

    private RandomForest buildClassifier(Instances instances) throws Exception {
        String[] options = new String[1];
        options[0] = "-U";
        RandomForest tree = new RandomForest();
        tree.buildClassifier(instances);
        return tree;
    }

    private void printInstances(Instances instances) {
        int numAttributes = instances.numAttributes();

        for (int i = 0; i < numAttributes; i++) {
            System.out.println("attribute " + i + " = " + instances.attribute(i));
        }

        System.out.println("class index = " + instances.classIndex());

        int numInstances = instances.numInstances();
        for (int i = 0; i < numInstances; i++) {
            if (i == 5) break;
            Instance instance = instances.instance(i);
            System.out.println("instance = " + instance);
        }
    }

    private Instances loadArff(String datafile) throws Exception {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();

            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("Could not read from file");
        }
    }
}
