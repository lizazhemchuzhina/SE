import java.util.HashMap;

public class LogService {
    private final HashMap<Integer, Log> logs = new HashMap<>();
    private int currentId = 0;

    public int size() {
        return logs.size();
    }

    public boolean contains(int id) {
        return logs.containsKey(id);
    }

    public int add(Log log) {
        logs.put(currentId, log);
        currentId++;
        return currentId - 1;
    }
}
