package hexlet.code;

public final class DifferItem {

    private final String key;
    private final Object value;
    private Object updatedValue;
    private final Action action;

    public DifferItem(String key, Object value, Action action) {
        this.key = key;
        this.value = value;
        this.action = action;
    }

    public DifferItem(String key, Object value, Action action, Object updatedValue) {
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
