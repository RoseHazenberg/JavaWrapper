package nl.bioinf.wrapper;

//import java.io.File;

public class AlgorithmEngine {
    void start(OptionsProvider optionsProvider) {
        String inputFile = optionsProvider.getFileName();
        int number = optionsProvider.getNumber();
        System.out.println("number: " + number);
        System.out.println("inputFile: " + inputFile);


    }
}
