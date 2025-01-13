package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    String jsonDiffFileName = "jsonDiff.txt";
    String jsonDiffResult = "";

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                    .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        jsonDiffResult = readFixture(jsonDiffFileName);
    }

    @Test
    void checkJsonFormatter() throws Exception {
        String fileName1 = "file1.json";
        String fileName2 = "file2.json";
        String formatName = null;
        String diff = Differ.generate(fileName1, fileName2, formatName);
        assertEquals(jsonDiffResult, diff);
    }
}
