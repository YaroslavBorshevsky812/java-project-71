package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.DiffFormatter;
import hexlet.code.formatters.JSONFormatter;
import hexlet.code.formatters.PlainFormatter;
import java.util.List;

public class Formatter {
    public static String getFormatter(List<DifferItem> differItemList, String formatName)
        throws JsonProcessingException {

        if (formatName == null) {
            return  DiffFormatter.generateDiff(differItemList);
        }

        switch (formatName) {
            case "plain" -> {
                return PlainFormatter.generatePlain(differItemList);
            }
            case "stylish" -> {
                return DiffFormatter.generateDiff(differItemList);
            }
            case "json" -> {
                return JSONFormatter.generateJson(differItemList);
            }
            default -> {
                throw new IllegalArgumentException("There is no such a format");
            }
        }
    }
}
