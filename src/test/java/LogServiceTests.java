import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
