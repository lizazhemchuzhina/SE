package Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionService {
    private static Map<String, List<String>> projectToWorkingGroup = new HashMap();

    public static void add(String projectName, List<String> workingGroups) {
        if (!projectToWorkingGroup.containsKey(projectName)) {
            projectToWorkingGroup.put(projectName, workingGroups);
            return;
        }
        projectToWorkingGroup.get(projectName).addAll(workingGroups);
    }

    public static List<String> getWorkingGroups(String projectName) {
        return projectToWorkingGroup.get(projectName);
    }

    public static void clear() {
        projectToWorkingGroup.clear();
    }
}

