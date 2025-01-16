package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferTest {

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                    .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @ParameterizedTest
    @CsvSource({
        "jsonDiff.txt, file1.json, file2.json, null",
        "yamlDiff.txt, filepath1.yml, filepath2.yml, null",
        "jsonNested.txt, nestedFile1.json, nestedFile2.json, null",
        "plainNestedDiff.txt, nestedFile1.json, nestedFile2.json, plain",
        "newJson.json, file1.json, file2.json, json",
        "jsonDiff.txt, file1.json, file2.json, stylish"
    })
    void checkFormatting(String expectedResultFileName, String fileName1, String fileName2, String format
    ) throws Exception {
        String expectedResult = readFixture(expectedResultFileName);
        Path filePath1 = getFixturePath(fileName1);
        Path filePath2 = getFixturePath(fileName2);

        String diff;
        if (Objects.equals(format, "null")) {
            diff = Differ.generate(filePath1.toString(), filePath2.toString());
        } else {
            diff = Differ.generate(filePath1.toString(), filePath2.toString(), format);
        }

        assertEquals(expectedResult, diff);
    }

    @ParameterizedTest
    @CsvSource({
        "file1.txt, file2.txt, Formats are not acceptable",
        "file1.yml, file2.json, You try to use files with different formats",
    })
    void checkIllegalArgumentExceptionException(String fileName1, String fileName2, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Differ.prepareListAccordingToFormat(fileName1, fileName2);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
