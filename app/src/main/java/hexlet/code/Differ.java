package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    private static Path getFilePath(String fileName) {
        return Paths.get("src", "main", "resources", "assets", fileName)
                    .toAbsolutePath().normalize();
    }

    public static String readFile(String fileName) throws Exception {
        var path = getFilePath(fileName);
        return Files.readString(path).trim();
    }

    public static TreeMap<String, Object> getData(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Тут используем Tree чтобы получить сортировку.
        return new TreeMap<String, Object>(mapper.readValue(jsonString, Map.class));
    }

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
                        .append(secondFileMap.get(key)).append("\n");

                    result.append(stringPlusKey).append(":").append(" ")
                          .append(entry.getValue()).append("\n");
                }
            }



        }

        return "{" + "\n" + result + "}";
    };
}
