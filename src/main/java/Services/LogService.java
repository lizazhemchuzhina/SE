package Services;

import Models.Labels;
import Models.Log;

import java.util.*;

public class LogService {
    private final HashMap<Integer, Log> logs = new HashMap<>();
    private int currentId;
    private int logLevelsUpperBound = 10;

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

    public int getLogLevel(int logId) {
        return logs.get(logId).getLevel();
    }

    public List<Integer> getLogLevel(List<Integer> logsId) {
        List<Integer> levelsList = new ArrayList<>();
        for (int id : logsId) {
            levelsList.add(getLogLevel(id));
        }
        return levelsList;
    }

    public boolean changeLogLevel(int logId, int level) {
        if (level < 0 || level > logLevelsUpperBound) {
            return false;
        }
        logs.get(logId).setLevel(level);
        return true;
    }

    public boolean changeLogLevel(List<Integer> logsId, int level) {
        for (Integer id : logsId) {
            if (!changeLogLevel(id, level)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getLogsByLevel(int level) {
        List<Integer> logsId = new ArrayList<>();
        for (Map.Entry<Integer, Log> map : logs.entrySet()) {
            if (map.getValue().getLevel() == level) {
                logsId.add(map.getKey());
            }
        }
        return logsId;
    }

    public List<Integer> getLogsByLabel(Labels label) {
        List<Integer> logsId = new ArrayList<>();
        for (Map.Entry<Integer, Log> map : logs.entrySet()) {
            if (map.getValue().getLabel() == label) {
                logsId.add(map.getKey());
            }
        }
        return logsId;
    }

    public boolean delete(int logId) {
        if (logs.containsKey(logId)) {
            logs.remove(logId);
            return true;
        }
        return false;
    }

    public boolean deleteByLabel(Labels label) {
        boolean wasDeletion = false;
        List<Integer> logsToDelete = getLogsByLabel(label);
        for (int id : logsToDelete) {
            if (delete(id)) {
                wasDeletion = true;
            }
        }
        return wasDeletion;
    }

    public List<Log> getAllLogs() {
        return new ArrayList<>(logs.values());
    }

    public List<Log> getLogsInInterval(Date left, Date right) {
        List<Log> logsInInterval = new ArrayList<>();
        for (Log log: logs.values()) {
            if (log.getDate() == null) {
                continue;
            }
            if (log.getDate().after(left) && log.getDate().before(right)) {
                logsInInterval.add(log);
            }
        }
        return logsInInterval;
    }
}
