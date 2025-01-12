package hexlet.code.formatters;

import hexlet.code.DifferItem;

import java.util.List;

public class DiffFormatter {

    public static String generateDiff(List<DifferItem> differItemList) {
        StringBuilder result = new StringBuilder();

        differItemList.forEach(differItem -> {
            String space = " ";
            String plus = space + "+" + space;
            String minus = space + "-" + space;
            String colon = ":";
            String stringItem = "";

            switch (differItem.getAction()) {
                case ADDED:
                    stringItem =
                        plus + differItem.getKey() + colon + differItem.getValue();
                    break;
                case DELETED:
                    stringItem =
                        minus + differItem.getKey() + colon + differItem.getValue();
                    break;
                case NOTHING:
                    stringItem =
                        space + space + space + differItem.getKey() + colon + differItem.getValue();
                    break;
                case UPDATED:
                    String previous = minus + differItem.getKey() + colon + differItem.getValue();
                    String newValue = plus + differItem.getKey() + colon + differItem.getUpdatedValue();
                    stringItem =
                        previous + "\n" + space + newValue;
                    break;
                default:
            }

            result.append(space).append(stringItem).append("\n");
        });

        return "{" + "\n" + result + "}";
    }
}
