package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class ChangeSet {
    private Map<String, Object> added = new HashMap<>();
    private Map<String, Object> modified = new HashMap<>();
    private Map<String, Object> removed = new HashMap<>();

    public ChangeSet() { };
    public Map<String, Object> getAdded() {
        return added;
    }

    public void setAdded(Map<String, Object> added) {
        this.added = added;
    }

    public Map<String, Object> getModified() {
        return modified;
    }

    public void setModified(Map<String, Object> modified) {
        this.modified = modified;
    }

    public Map<String, Object> getRemoved() {
        return removed;
    }

    public void setRemoved(Map<String, Object> removed) {
        this.removed = removed;
    }
}
