import Models.FileWrapper;
import Services.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileServiceTests {
    @Test
    public void testFileWrapperName() {
        FileWrapper file = new FileWrapper("path_to_file");
        Assertions.assertEquals("path_to_file", file.getPath().toString());
    }

    @Test
    public void testFileWrapperNumberOfLogs() {
        FileWrapper file = new FileWrapper("path_to_file");
        file.addLog("ERROR: expected X, but got Y");
        file.addLog("WARNING: winter is coming");
        Assertions.assertEquals(2, file.numberOfLogs());
    }

    @Test
    public void testFileWrapperContainsLogs() {
        FileWrapper file = new FileWrapper("path_to_file");
        Integer firstLogId = file.addLog("ERROR: expected X, but got Y");
        Integer secondLogId = file.addLog("WARNING: winter is coming");

        Assertions.assertTrue(file.contains(firstLogId));
        Assertions.assertTrue(file.contains(secondLogId));

    }

    @Test
    public void testFileWrapperContainsLogsList() {
        FileWrapper file = new FileWrapper("path_to_file");
        Integer firstLogId = file.addLog("ERROR: expected X, but got Y");
        Integer secondLogId = file.addLog("WARNING: winter is coming");

        Assertions.assertTrue(file.contains(
                new ArrayList<>(Arrays.asList(firstLogId, secondLogId)))
        );
        Assertions.assertFalse(file.contains(new ArrayList<>(Arrays.asList(firstLogId, secondLogId,
                firstLogId + secondLogId + 1))));
    }

    @Test
    public void testAddFileServiceCheckNumber() {
        FileWrapper file1 = new FileWrapper("path_to_file1");
        FileWrapper file2 = new FileWrapper("path_to_file2");
        FileWrapper file3 = new FileWrapper("path_to_file3");

        FileService.add("project1", new ArrayList<>(Arrays.asList(file1, file2)));
        FileService.add("project2", new ArrayList<>(List.of(file3)));

        Assertions.assertEquals(2, FileService.getFilesAmount("project1"));
        Assertions.assertEquals(1, FileService.getFilesAmount("project2"));
        Assertions.assertEquals(0, FileService.getFilesAmount("project3"));
    }

    @Test
    public void testAddFileServiceCheckContent() {
        FileWrapper file1 = new FileWrapper("path_to_file1");
        FileWrapper file2 = new FileWrapper("path_to_file2");
        FileWrapper file3 = new FileWrapper("path_to_file3");

        FileService.add("project3", new ArrayList<>(Arrays.asList(file1, file2)));
        FileService.add("project4", new ArrayList<>(List.of(file3)));

        List<FileWrapper> filesInProject1 = FileService.getFiles("project3");
        List<FileWrapper> expectedFiles = new ArrayList<>(Arrays.asList(file1, file2));
        Assertions.assertEquals(expectedFiles.size(), filesInProject1.size());
        Assertions.assertTrue(expectedFiles.containsAll(filesInProject1));
    }

    @Test
    public void testAddFileServiceCheckContentEmpty() {
        Assertions.assertEquals(0, FileService.getFiles("projectX").size());
    }
}
