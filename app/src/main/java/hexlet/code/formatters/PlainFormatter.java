package hexlet.code.formatters;

import hexlet.code.DifferItem;
import java.util.List;

public class PlainFormatter {

    public static String generatePlain(List<DifferItem> differItemList) {
        StringBuilder result = new StringBuilder();

        differItemList.forEach(differItem -> {
            String stringItem = null;

            String value = differItem.getValue() + "";

            String updatedValue = differItem.getUpdatedValue() + "";


            switch (differItem.getAction()) {
                case ADDED:
                    stringItem = String.format("Property '%s' was added with value: [%s]",
                                               differItem.getKey(),
                                               value);
                    break;
                case DELETED:
                    stringItem = String.format("Property '%s' was removed", differItem.getKey());
                    break;
                case UPDATED:
                    stringItem = String.format("Property '%s' was updated. From [%s] to %s",
                                               differItem.getKey(),
                                               value,
                                               updatedValue);
                    break;
                default:
            }

            if (stringItem != null) {
                result.append(" ").append(stringItem).append("\n");
            }
        });

        return "{" + "\n" + result + "}";
    }
}
