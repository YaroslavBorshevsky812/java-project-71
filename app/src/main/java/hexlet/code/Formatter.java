package hexlet.code;

import hexlet.code.formatters.DiffFormatter;
import hexlet.code.formatters.PlainFormatter;
import java.util.List;

public class Formatter {
    public static String getFormatter(List<DifferItem> differItemList, String formatName) {
        String result = "";



        if (formatName == null) {
            result = DiffFormatter.generateDiff(differItemList);
        } else {
            switch (formatName) {
                case "plain" -> {
                    result = PlainFormatter.generatePlain(differItemList);
                }
                case "json" -> System.out.println("diff");
                default -> {
                    System.out.println("default");
                }
            }
        }

        return result;
    }
}
