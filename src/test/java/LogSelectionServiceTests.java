import Models.Labels;
import Models.Log;
import Services.LogSelectionService;
import Services.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class LogSelectionServiceTests {
    @Test
    public void testSelectLogs() {
        Log log1 = new Log("ERROR: all fell and Liza is a cow bigger then Zhenya");
        Log log2 = new Log("ERROR: all fell and Zhenya is a cow");
        Log log3 = new Log("ERROR: all fell and we are not happy");
        Assertions.assertEquals(0, LogSelectionService.select(Collections.emptyList()).size());
        List<Log> logsInputModel = new ArrayList<>(Arrays.asList(log1, log2, log3));
        List<Log> expectedLogsOutputModel = new ArrayList<>(Arrays.asList(log1, log3));
        Assertions.assertEquals(expectedLogsOutputModel.size(), LogSelectionService.select(logsInputModel).size());
        Assertions.assertTrue(expectedLogsOutputModel.containsAll(LogSelectionService.select(logsInputModel)));
    }

}
