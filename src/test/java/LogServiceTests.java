import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogServiceTests {
    LogService logService;

    @BeforeEach
    public void createLogService() {
        logService = new LogService();
    }

    @Test
    public void addLog() {
        Assertions.assertEquals(0, logService.size());
        Log log = new Log("ERROR: all fell");
        int logId = logService.add(log);
        Assertions.assertTrue(logService.contains(logId));
        Assertions.assertFalse(logService.contains(123));
        Log log2 = new Log("INFO: aaaaaaaa");
        logService.add(log2);
        Assertions.assertEquals(2, logService.size());
    }

    @Test
    public void changeLogLevel() {
        Log log = new Log("ERROR: all fell");
        int logId = logService.add(log);
        Assertions.assertEquals(0, logService.getLogLevel(logId));
        Assertions.assertFalse(logService.changeLogLevel(logId, 123));
        Assertions.assertTrue(logService.changeLogLevel(logId, 3));
        Log log2 = new Log("ERROR: all fell and Zhenya is a cow");
        Log log3 = new Log("ERROR: all fell and we are not happy");
        List<Integer> logsId = new ArrayList<>(Arrays.asList(0, logService.add(log2), logService.add(log3)));
        Assertions.assertTrue(logService.changeLogLevel(logsId, 4));
        List<Integer> expectedLogsId = new ArrayList<>(Arrays.asList(4, 4, 4));
        Assertions.assertEquals(expectedLogsId, logService.getLogLevel(logsId));
    }

    @Test
    public void getLogsByLevel() {
        Log log = new Log("ERROR: all fell");
        int logId = logService.add(log);

        Log log1 = new Log("ERROR: all fell and Liza is a cow bigger then Zhenya");
        Log log2 = new Log("ERROR: all fell and Zhenya is a cow");
        Log log3 = new Log("ERROR: all fell and we are not happy");
        List<Integer> logsId = new ArrayList<>(Arrays.asList(logService.add(log1), logService.add(log2), logService.add(log3)));
        logService.changeLogLevel(logsId, 2);

        List<Integer> logsLevel0 = logService.getLogsByLevel(0);
        Assertions.assertEquals(1, logsLevel0.size());
        Assertions.assertEquals(logId, logsLevel0.get(0));

        List<Integer> logsLevel2 = logService.getLogsByLevel(2);
        Assertions.assertEquals(logsId.size(), logsLevel2.size());
        Assertions.assertTrue(logsLevel2.containsAll(logsId));

        Assertions.assertEquals(0, logService.getLogsByLevel(1).size());
    }

    @Test
    public void getLogsByLabel() {

        Log log1 = new Log("ERROR: all fell and Liza is a cow bigger then Zhenya");
        Log log2 = new Log("ERROR: all fell and Zhenya is a cow");
        Log log3 = new Log("ERROR: all fell and we are not happy");
        List<Integer> expectedLogsIdErr = new ArrayList<>(Arrays.asList(logService.add(log1), logService.add(log2), logService.add(log3)));
        List<Integer> logsIdErr = logService.getLogsByLabel(Labels.ERROR);
        Assertions.assertEquals(expectedLogsIdErr.size(), logsIdErr.size());
        Assertions.assertTrue(expectedLogsIdErr.containsAll(logsIdErr));

        Log log4 = new Log("INFO: all fell and Liza is a cow bigger then Zhenya");
        Log log5 = new Log("WARNING: all fell and Zhenya is a cow");
        Log log6 = new Log("WARNING: all fell and we are not happy");

        List<Integer> expectedLogsIdInfo = new ArrayList<>(List.of(logService.add(log4)));
        List<Integer> logsIdInfo = logService.getLogsByLabel(Labels.INFO);
        Assertions.assertEquals(expectedLogsIdInfo.size(), logsIdInfo.size());
        Assertions.assertTrue(expectedLogsIdInfo.containsAll(logsIdInfo));

        List<Integer> expectedLogsIdWarning = new ArrayList<>(Arrays.asList(logService.add(log5), logService.add(log6)));
        List<Integer> logsIdWarning = logService.getLogsByLabel(Labels.WARNING);
        Assertions.assertEquals(expectedLogsIdWarning.size(), logsIdWarning.size());
        Assertions.assertTrue(expectedLogsIdWarning.containsAll(logsIdWarning));

        List<Integer> logsIdTB = logService.getLogsByLabel(Labels.TRACEBACK);
        Assertions.assertEquals(0,logsIdTB.size());
    }
}
