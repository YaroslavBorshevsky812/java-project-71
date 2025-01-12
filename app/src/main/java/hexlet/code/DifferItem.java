package hexlet.code;

public class DifferItem {

    public String key;
    public Object value;
    public Object updatedValue;
    public Action action;

    public DifferItem(String key, String value, Action action) {
        this.key = key;
        this.value = value;
        this.action = action;
    }

    public DifferItem(String key, String value, Action action, String updatedValue) {
        this.key = key;
        this.value = value;
        this.updatedValue = updatedValue;
        this.action = action;
    }

    public Object getUpdatedValue() {
        return updatedValue;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Action getAction() {
        return action;
    }
}
