import Models.Labels;
import Models.Log;
import Services.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogServiceTests {
    private LogService logService;

    @BeforeEach
    public void createLogService() {
        logService = new LogService();
    }

    @Test
    public void testLogServiceEmptyAfterCreation() {
        Assertions.assertEquals(0, logService.size());
    }

    @Test
    public void testContainsTrue() {
        Log log = new Log("ERROR: all fell");
        int logId = logService.add(log);
        Assertions.assertTrue(logService.contains(logId));
    }

    @Test
    public void testContainsFalse() {
        Assertions.assertFalse(logService.contains(123));
    }

    @Test
    public void testSize() {
        logService.add(new Log("ERROR: all fell"));
        logService.add(new Log("INFO: aaaaaaaa"));
        Assertions.assertEquals(2, logService.size());
    }

    @Test
    public void testChangeLogLevelToMoreThanAllowed() {
        int logId = logService.add(new Log("ERROR: all fell"));
        Assertions.assertFalse(logService.changeLogLevel(logId, 123));
    }

    @Test
    public void testChangeLogLevelOk() {
        int logId = logService.add(new Log("ERROR: all fell"));
        Assertions.assertTrue(logService.changeLogLevel(logId, 3));
    }


    @Test
    public void testChangeLogLevelOkList() {
        Log log2 = new Log("ERROR: all fell");
        Log log3 = new Log("ERROR: all fell");

        List<Integer> logsId = new ArrayList<>(Arrays.asList(logService.add(log2),
                logService.add(log3)));
        Assertions.assertTrue(logService.changeLogLevel(logsId, 4));
        List<Integer> expectedLogsId = new ArrayList<>(Arrays.asList(4, 4));
        Assertions.assertEquals(expectedLogsId, logService.getLogLevel(logsId));
    }

    @Test
    public void testGetLogsByLevelEmptyList() {
        Assertions.assertEquals(0, logService.getLogsByLevel(1).size());
    }

    @Test
    public void testGetLogsByLevel() {
        int logId = logService.add(new Log("ERROR: all fell"));
        List<Integer> logsId = new ArrayList<>(Arrays.asList(
                logService.add(new Log("ERROR: all fell")),
                logService.add(new Log("ERROR: something broke")),
                logService.add(new Log("ERROR: something does not seem right"))));
        logService.changeLogLevel(logsId, 2);

        List<Integer> logsLevel0 = logService.getLogsByLevel(0);
        Assertions.assertEquals(1, logsLevel0.size());
        Assertions.assertEquals(logId, logsLevel0.get(0));

        List<Integer> logsLevel2 = logService.getLogsByLevel(2);
        Assertions.assertEquals(logsId.size(), logsLevel2.size());
        Assertions.assertTrue(logsLevel2.containsAll(logsId));
    }

    @Test
    public void testGetLogsByLabelNonEmpty() {
        List<Integer> expectedLogsIdErr = new ArrayList<>(Arrays.asList(
                logService.add(new Log("ERROR: all fell")),
                logService.add(new Log("ERROR: something broke")),
                logService.add(new Log("ERROR: something does not seem right"))));
        List<Integer> logsIdErr = logService.getLogsByLabel(Labels.ERROR);
        Assertions.assertEquals(expectedLogsIdErr.size(), logsIdErr.size());
        Assertions.assertTrue(expectedLogsIdErr.containsAll(logsIdErr));
    }

    @Test
    public void testGetLogsByLabelEmptyList() {
        List<Integer> logsIdTB = logService.getLogsByLabel(Labels.TRACEBACK);
        Assertions.assertEquals(0, logsIdTB.size());
    }

    @Test
    public void testDeleteByLabel() {
        logService.add(new Log("ERROR: all fell"));
        logService.add(new Log("WARNING: might fell"));
        logService.add(new Log("INFO: didn't fall"));
        logService.add(new Log("INFO: sth happened"));
        Assertions.assertEquals(4, logService.size());
        Assertions.assertTrue(logService.deleteByLabel(Labels.INFO));
        Assertions.assertEquals(2, logService.size());
        Assertions.assertFalse(logService.deleteByLabel(Labels.TRACEBACK));
        Assertions.assertEquals(2, logService.size());
    }

    @Test
    public void testDelete() {
        int logId = logService.add(new Log("ERROR: all fell"));
        Assertions.assertEquals(1, logService.size());
        Assertions.assertTrue(logService.delete(logId));
        Assertions.assertEquals(0, logService.size());
        Assertions.assertFalse(logService.delete(logId));
    }
}
