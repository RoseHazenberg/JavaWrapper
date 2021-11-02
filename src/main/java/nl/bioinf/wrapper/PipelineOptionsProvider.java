package nl.bioinf.wrapper;

public class PipelineOptionsProvider implements OptionsProvider {
    @Override
    public int getNumber() {
        return 42;
    }

    @Override
    public String getFileName() {
        return "demo_data.txt";
    }
}
