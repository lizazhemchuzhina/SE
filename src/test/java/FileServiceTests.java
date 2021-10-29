import Services.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FileServiceTests {
    @Test
    public void addFilesForGroup() {
        FileWrapper file = new FileWrapper("path_to_file");
        Assertions.assertEquals("path_to_file", file.getPath().toString());
        Integer firstLogId = file.addLog("ERROR: expected X, but got Y");
        Integer secondLogId = file.addLog("WARNING: winter is coming");
        Assertions.assertEquals(2, file.numberOfLogs());
        Assertions.assertTrue(file.contains(firstLogId));
        Assertions.assertTrue(file.contains(secondLogId));
        Assertions.assertTrue(file.contains(new ArrayList<>(Arrays.asList(firstLogId, secondLogId))));
        Assertions.assertTrue(file.contains(new ArrayList<>(Arrays.asList(firstLogId, secondLogId, firstLogId + secondLogId))));
    }
}
