package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Action;
import hexlet.code.ChangeSet;
import hexlet.code.DifferItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFormatter {

    public static ChangeSet generateMap(List<DifferItem> differItemList) {
        ChangeSet changeSet = new ChangeSet();

        // Added
        Map<String, Object> added = new HashMap<>();
        differItemList
            .stream()
            .filter(differItem -> Action.ADDED.equals(differItem.getAction())).
            forEach(differItem -> {
                added.put(differItem.getKey(), differItem);
            });
        changeSet.setAdded(added);

        // Updated
        Map<String, Object> modified = new HashMap<>();
        differItemList
            .stream()
            .filter(differItem -> Action.UPDATED.equals(differItem.getAction())).
            forEach(differItem -> {
                modified.put(differItem.getKey(), differItem);
            });
        changeSet.setModified(modified);

        // Deleted
        Map<String, Object> removed = new HashMap<>();
        differItemList
            .stream()
            .filter(differItem -> Action.DELETED.equals(differItem.getAction())).
            forEach(differItem -> {
                removed.put(differItem.getKey(), differItem);
            });
        changeSet.setRemoved(removed);

        return changeSet;
    }

    public static String generateJson(List<DifferItem> differItemList) throws JsonProcessingException {
        ChangeSet changeSet = JSONFormatter.generateMap(differItemList);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(changeSet);
    }

}
