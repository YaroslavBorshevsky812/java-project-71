package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {

    private static final String FORMATS = ".*\\.(json|yml)$";
    private static final String YAML_FORMAT = ".*\\.(yml)$";
    private static final String JSON_FORMAT = ".*\\.(json)$";

    private static Path getFilePath(String fileName) {
        return Paths.get(fileName)
                    .toAbsolutePath().normalize();
    }

    public static String readFile(String fileName) throws IOException {
        var path = getFilePath(fileName);

        return Files.readString(path).trim();
    }

    public static Map<String, Object> getJsonData(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Map.class);
    }

    public static Map<String, Object> getYmlData(String fileName) throws IOException {
        var yamlFilePath = getFilePath(fileName);
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(String.valueOf(yamlFilePath));
        return yaml.load(inputStream);
    }

    public static List<DifferItem> prepareListAccordingToFormat(
        String firstFilePath,
        String secondFilePath,
        String formatName
    ) throws IOException {

        List<DifferItem> result = new ArrayList<>();
        boolean isFormatAcceptable = firstFilePath.matches(FORMATS) && secondFilePath.matches(FORMATS);
        boolean isFormatsTheSame =
            (firstFilePath.matches(YAML_FORMAT) && secondFilePath.matches(YAML_FORMAT)
                || firstFilePath.matches(JSON_FORMAT) && secondFilePath.matches(JSON_FORMAT)
            );

        if (!isFormatAcceptable || !isFormatsTheSame) {
            throw new IllegalArgumentException("Formats are not acceptable");
        }

        if (firstFilePath.matches(YAML_FORMAT) && secondFilePath.matches(YAML_FORMAT)) {
            Map<String, Object> firstMap = Differ.getYmlData(firstFilePath);
            Map<String, Object> secondMap = Differ.getYmlData(secondFilePath);

            result = Differ.generateDifferItemList(firstMap, secondMap, formatName);
            return result;
        }

        if (firstFilePath.matches(JSON_FORMAT) && secondFilePath.matches(JSON_FORMAT)) {
            Map<String, Object> firstMap = Differ.getJsonData(Differ.readFile(firstFilePath));
            Map<String, Object> secondMap = Differ.getJsonData(Differ.readFile(secondFilePath));

            result = Differ.generateDifferItemList(firstMap, secondMap, formatName);
            return result;
        }

        return result;
    };

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        List<DifferItem> differItemList = Differ.prepareListAccordingToFormat(filePath1, filePath2, formatName);

        return Formatter.getFormatter(differItemList, formatName);
    };

    public static String generate(String filePath1, String filePath2) throws Exception {
        List<DifferItem> differItemList = Differ.prepareListAccordingToFormat(filePath1, filePath2, null);

        return Formatter.getFormatter(differItemList, null);
    };

    public static List<DifferItem> generateDifferItemList(
        Map<String, Object> firstFileMap,
        Map<String, Object> secondFileMap,
        String formatName
    ) {
        List<DifferItem> resultDifferList = new ArrayList<>();

        for (String firstFileMapKey : firstFileMap.keySet()) {
            Object firstMapValue = firstFileMap.get(firstFileMapKey);
            Object secondMapValue = secondFileMap.get(firstFileMapKey);

            // Removal.
            if (!secondFileMap.containsKey(firstFileMapKey)) {
                DifferItem differItem =
                    new DifferItem(firstFileMapKey, firstMapValue, Action.DELETED);
                resultDifferList.add(differItem);

            } else {
                // Nothing has done with the line.
                if (Objects.equals(firstMapValue, secondMapValue)) {
                    DifferItem differItem =
                        new DifferItem(firstFileMapKey, firstMapValue, Action.NOTHING);
                    resultDifferList.add(differItem);
                }

                // Line has been changed.
                if (!Objects.equals(firstMapValue, secondMapValue)) {

                    DifferItem differItem =
                        new DifferItem(firstFileMapKey, firstMapValue, Action.UPDATED, secondMapValue);
                    resultDifferList.add(differItem);
                }
            }
        }

        // Here we're checking if a new line has been added to the file.
        for (String secondMapKey : secondFileMap.keySet()) {

            if (!firstFileMap.containsKey(secondMapKey)) {
                DifferItem differItem =
                    new DifferItem(secondMapKey, secondFileMap.get(secondMapKey), Action.ADDED);
                resultDifferList.add(differItem);
            }
        }

        resultDifferList.sort((item1, item2) -> item1.getKey().compareTo(item2.getKey()));

        return resultDifferList;
    }
}
