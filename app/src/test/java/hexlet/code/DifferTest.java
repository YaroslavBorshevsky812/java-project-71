package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    String jsonDiffFileName = "jsonDiff.txt";
    String yamlDiffFileName = "yamlDiff.txt";
    String jsonNestedDiffFileName = "jsonNested.txt";
    String plainNestedDiffFileName = "plainNestedDiff.txt";

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                    .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @Test
    void checkIfJsonFileFormattingCorrect() throws Exception {
        String jsonDiffResult = readFixture(jsonDiffFileName);
        String fileName1 = "file1.json";
        String fileName2 = "file2.json";
        String diff = Differ.generate(fileName1, fileName2, null);
        assertEquals(jsonDiffResult, diff);
    }

    @Test
    void checkIfYamlFileFormattingCorrect() throws Exception {
        String yamlDiffResult = readFixture(yamlDiffFileName);
        String fileName1 = "filepath1.yml";
        String fileName2 = "filepath2.yml";
        String diff = Differ.generate(fileName1, fileName2, null);
        assertEquals(yamlDiffResult, diff);
    }

    @Test
    void checkIfNestedFileFormattingCorrect() throws Exception {
        String jsonNestedDiffResult = readFixture(jsonNestedDiffFileName);
        String fileName1 = "nestedFile1.json";
        String fileName2 = "nestedFile2.json";
        String diff = Differ.generate(fileName1, fileName2, null);
        assertEquals(jsonNestedDiffResult, diff);
    }

    @Test
    void checkPlainFormatter() throws Exception {
        String plainNestedDiffResult = readFixture(plainNestedDiffFileName);
        String fileName1 = "nestedFile1.json";
        String fileName2 = "nestedFile2.json";
        String diff = Differ.generate(fileName1, fileName2, "plain");
        assertEquals(plainNestedDiffResult, diff);
    }
}
