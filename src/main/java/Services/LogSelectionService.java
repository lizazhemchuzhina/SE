package Services;

import Models.Log;

import java.util.ArrayList;
import java.util.List;

public class LogSelectionService {
    public static List<Log> select(List<Log> logs) {
        List<Log> modelOutput = new ArrayList<>();
        if (logs.size() == 0) {
            return modelOutput;
        }
        modelOutput.add(logs.get(0));
        modelOutput.add(logs.get(logs.size() - 1));
        return modelOutput;
    }
}

