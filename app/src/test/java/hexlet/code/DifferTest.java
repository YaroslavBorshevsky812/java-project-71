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
    String jsonFormatterResult = "newJson.json";

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
        String fileName1 = "src/main/resources/assets/file1.json";
        String fileName2 = "src/main/resources/assets/file2.json";
        String diff = Differ.generate(fileName1, fileName2);
        assertEquals(jsonDiffResult, diff);
    }

    @Test
    void checkIfYamlFileFormattingCorrect() throws Exception {
        String yamlDiffResult = readFixture(yamlDiffFileName);
        String fileName1 = "src/main/resources/assets/filepath1.yml";
        String fileName2 = "src/main/resources/assets/filepath2.yml";
        String diff = Differ.generate(fileName1, fileName2);
        assertEquals(yamlDiffResult, diff);
    }

    @Test
    void checkIfNestedFileFormattingCorrect() throws Exception {
        String jsonNestedDiffResult = readFixture(jsonNestedDiffFileName);
        String fileName1 = "src/main/resources/assets/nestedFile1.json";
        String fileName2 = "src/main/resources/assets/nestedFile2.json";
        String diff = Differ.generate(fileName1, fileName2);
        assertEquals(jsonNestedDiffResult, diff);
    }

    @Test
    void checkPlainFormatter() throws Exception {
        String plainNestedDiffResult = readFixture(plainNestedDiffFileName);
        String fileName1 = "src/main/resources/assets/nestedFile1.json";
        String fileName2 = "src/main/resources/assets/nestedFile2.json";
        String diff = Differ.generate(fileName1, fileName2, "plain");
        assertEquals(plainNestedDiffResult, diff);
    }

    @Test
    void checkJSONFormatter() throws Exception {
        String jsonFormatterExpectedResult = readFixture(jsonFormatterResult);
        String fileName1 = "src/main/resources/assets/file1.json";
        String fileName2 = "src/main/resources/assets/file2.json";
        String diff = Differ.generate(fileName1, fileName2, "json");
        assertEquals(jsonFormatterExpectedResult, diff);
    }

    @Test
    void checkStylishCase() throws Exception {
        String jsonFormatterExpectedResult = readFixture(jsonDiffFileName);
        String fileName1 = "src/main/resources/assets/file1.json";
        String fileName2 = "src/main/resources/assets/file2.json";
        String diff = Differ.generate(fileName1, fileName2, "stylish");
        assertEquals(jsonFormatterExpectedResult, diff);
    }
}
