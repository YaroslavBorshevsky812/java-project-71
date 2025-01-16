package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFormatter {

    public static String generateJson(List<DifferItem> differItemList) throws JsonProcessingException {
        Map<String, Object> resultMap = new HashMap<>();
        differItemList.forEach(differItem -> {
            resultMap.put(differItem.getKey(), differItem);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
    }

}
