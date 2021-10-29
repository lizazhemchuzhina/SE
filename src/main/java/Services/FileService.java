package Services;

import Models.FileWrapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileService {
    private static HashMap<String, List<FileWrapper>> projects = new HashMap<>();

    public static void add(String projectName, List<FileWrapper> fileList) {
        if (!projects.containsKey(projectName)) {
            projects.put(projectName, fileList);
            return;
        }
        projects.get(projectName).addAll(fileList);
    }

    public static int getFilesAmount(String projectName) {
        if (!projects.containsKey(projectName)) {
            return 0;
        }
        return projects.get(projectName).size();
    }

    public static List<FileWrapper> getFiles(String projectName) {
        if (!projects.containsKey(projectName)) {
            return Collections.emptyList();
        }
        return projects.get(projectName);
    }
}
