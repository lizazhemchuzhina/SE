package Models;

import Services.LogService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWrapper {
    private Path pathToFile;
    private LogService logService = new LogService();

    public FileWrapper(String pathToFile) {
        this.pathToFile = Paths.get(pathToFile);
    }

    public Path getPath() {
        return pathToFile;
    }

    public Integer addLog(String logMessage) {
        Log log = new Log(logMessage);
        return logService.add(log);
    }

    public int numberOfLogs() {
        return logService.size();
    }

    public boolean contains(Integer logId) {
        return logService.contains(logId);
    }

    public boolean contains(List<Integer> logsId) {
        for (Integer id : logsId) {
            if (!logService.contains(id)) {
                return false;
            }
        }
        return true;
    }

    public List<Log> getAllLogs() {
        return logService.getAllLogs();
    }
}
