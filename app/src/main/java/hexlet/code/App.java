package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "checksum 4.0",
    description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-a", "--format"}, description = "output format [default: stylish]")
    private String format;

    @Override
    public void run() {
        try {
            Differ.checkFormat(filepath1, filepath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
