package hexlet.code.formatters;

import hexlet.code.DifferItem;
import java.util.List;

public class PlainFormatter {

    public static Object getValue(Object value) {
        if (value == null || value.getClass().equals(Boolean.class)) {
            return value + "";
        }

        if (value instanceof String) {
            return String.format("'%s'", value);
        }

        return value instanceof Integer ? value : "[complex value]";
    }

    public static String generatePlain(List<DifferItem> differItemList) {
        StringBuilder result = new StringBuilder();

        differItemList.forEach(differItem -> {
            String stringItem = null;
            Object value = PlainFormatter.getValue(differItem.getValue());
            Object updatedValue = PlainFormatter.getValue(differItem.getUpdatedValue());


            switch (differItem.getAction()) {
                case ADDED:
                    stringItem = String.format("Property '%s' was added with value: %s",
                                               differItem.getKey(),
                                               value);
                    break;
                case DELETED:
                    stringItem = String.format("Property '%s' was removed", differItem.getKey());
                    break;
                case UPDATED:
                    stringItem = String.format("Property '%s' was updated. From %s to %s",
                                               differItem.getKey(),
                                               value,
                                               updatedValue);
                    break;
                default:
            }

            if (stringItem != null) {
                result.append(stringItem).append("\n");
            }
        });

        return ("\n" + result).trim();
    }
}
