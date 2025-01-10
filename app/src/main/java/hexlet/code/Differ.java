package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    private static final String FORMATS = ".*\\.(json|yml)$";
    private static final String YAML_FORMAT = ".*\\.(yml)$";
    private static final String JSON_FORMAT = ".*\\.(json)$";

    private static Path getFilePath(String fileName) {
        return Paths.get("src", "main", "resources", "assets", fileName)
                    .toAbsolutePath().normalize();
    }

    public static String getSomethingForTest(String testString) {
        return testString;
    }

    public static String readFile(String fileName) throws Exception {
        var path = getFilePath(fileName);
        return Files.readString(path).trim();
    }

    public static TreeMap<String, Object> getJsonData(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Тут используем Tree чтобы получить сортировку.
        return new TreeMap<String, Object>(mapper.readValue(jsonString, Map.class));
    }

    public static TreeMap<String, Object> getYmlData(String fileName) throws IOException {
        var yamlFilePath = getFilePath(fileName);
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(String.valueOf(yamlFilePath));
        Map<String, Object> yamlMap = yaml.load(inputStream);
        // Тут используем Tree чтобы получить сортировку.
        return new TreeMap<String, Object>(yamlMap);
    }

    public static void checkFormat(String firstFilePath, String secondFilePath) throws Exception {

        if (firstFilePath.matches(FORMATS) && secondFilePath.matches(FORMATS)) {

            if (firstFilePath.matches(YAML_FORMAT) && secondFilePath.matches(YAML_FORMAT)) {
                TreeMap<String, Object> firstMap = Differ.getYmlData(firstFilePath);
                TreeMap<String, Object> secondMap = Differ.getYmlData(secondFilePath);

                System.out.println(Differ.generate(firstMap, secondMap));
                return;
            }

            if (firstFilePath.matches(JSON_FORMAT) && secondFilePath.matches(JSON_FORMAT)) {
                TreeMap<String, Object> firstMap = Differ.getJsonData(Differ.readFile(firstFilePath));
                TreeMap<String, Object> secondMap = Differ.getJsonData(Differ.readFile(secondFilePath));

                System.out.println(Differ.generate(firstMap, secondMap));
                return;
            }

            System.out.println("Разные форматы");

        } else {
            System.out.println("Формат не соответствует допустимому");
        }
    };

    public static String generate(Map<String, Object> firstFileMap, Map<String, Object> secondFileMap) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : firstFileMap.entrySet()) {
            String key = entry.getKey();
            String firstStringValue = entry.getValue() + "";
            String secondStringValue = secondFileMap.get(key) + "";

            String stringMinusKey = " " + " " + "- " + key;
            String stringPlusKey = " " + " " + "+ " + key;

            if (!secondFileMap.containsKey(key)) {
                result.append(stringMinusKey).append(":").append(" ").append(entry.getValue()).append("\n");
            } else {
                if (firstStringValue.equals(secondStringValue)) {
                    result.append(" ").append(" ").append(" ").append(" ")
                          .append(key)
                          .append(":")
                          .append(" ")
                          .append(entry.getValue()).append("\n");
                }

                if (!firstStringValue.equals(secondStringValue)) {
                    result.append(stringMinusKey).append(":").append(" ")
                          .append(entry.getValue()).append("\n");

                    result.append(stringPlusKey).append(":").append(" ")
                          .append(secondFileMap.get(key)).append("\n");
                }
            }
        }

        for (String secondKey : secondFileMap.keySet()) {
            String stringPlusKey = " " + " " + "+ " + secondKey;

            if (!firstFileMap.containsKey(secondKey)) {
                result.append(stringPlusKey).append(":").append(" ")
                      .append(secondFileMap.get(secondKey)).append("\n");
            }
        }

        return "{" + "\n" + result + "}";
    }
}
