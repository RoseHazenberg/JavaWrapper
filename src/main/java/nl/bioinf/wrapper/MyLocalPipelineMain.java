package nl.bioinf.wrapper;

public class MyLocalPipelineMain {
    public static void main(String[] args) {
        AlgorithmEngine engine = new AlgorithmEngine();
        engine.start(new PipelineOptionsProvider());
    }
}
