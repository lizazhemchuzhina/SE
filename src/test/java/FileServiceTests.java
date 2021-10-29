import Models.FileWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileServiceTests {
    @Test
    public void testFileWrapper() {
        FileWrapper file = new FileWrapper("path_to_file");
        Assertions.assertEquals("path_to_file", file.getPath().toString());
        Integer firstLogId = file.addLog("ERROR: expected X, but got Y");
        Integer secondLogId = file.addLog("WARNING: winter is coming");
        Assertions.assertEquals(2, file.numberOfLogs());
        Assertions.assertTrue(file.contains(firstLogId));
        Assertions.assertTrue(file.contains(secondLogId));
        Assertions.assertTrue(file.contains(new ArrayList<>(Arrays.asList(firstLogId, secondLogId))));
        Assertions.assertFalse(file.contains(new ArrayList<>(Arrays.asList(firstLogId, secondLogId, firstLogId + secondLogId + 1))));
    }

    @Test
    public void testAddFileService() {
        FileWrapper file1 = new FileWrapper("path_to_file1");
        FileWrapper file2 = new FileWrapper("path_to_file2");
        FileWrapper file3 = new FileWrapper("path_to_file3");

        FileService.add("project1", new ArrayList<>(Arrays.asList(file1, file2)));
        FileService.add("project2", new ArrayList<>(List.of(file3)));

        Assertions.assertEquals(2, FileService.getFilesAmount("project1"));
        Assertions.assertEquals(1, FileService.getFilesAmount("project2"));
        Assertions.assertEquals(0, FileService.getFilesAmount("project3"));

        List<FileWrapper> filesInProject1 = FileService.getFiles("project1");
        List<FileWrapper> expectedFiles = new ArrayList<>(Arrays.asList(file1, file2));
        Assertions.assertEquals(expectedFiles.size(), filesInProject1.size());
        Assertions.assertTrue(expectedFiles.containsAll(filesInProject1));

        Assertions.assertEquals(0, FileService.getFiles("project3").size());
    }
}
