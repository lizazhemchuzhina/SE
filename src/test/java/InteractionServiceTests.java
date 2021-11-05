
import Services.InteractionService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


public class InteractionServiceTests {
    @BeforeEach
    public void clear() {
        InteractionService.clear();
    }

    @Test
    public void testGetWorkingGroups() {
        InteractionService.add("project1", new ArrayList<>(Arrays.asList("working-group-1", "working-group-2")));
        InteractionService.add("project2", new ArrayList<>(List.of("working-group-3")));
        List<String> expectedWorkingGroupsProject2 = new ArrayList<>(List.of("working-group-3"));
        List<String> expectedWorkingGroupsProject1 = new ArrayList<>(Arrays.asList("working-group-1", "working-group-2"));
        Assertions.assertEquals(expectedWorkingGroupsProject1.size(), InteractionService.getWorkingGroups("project1").size());
        Assertions.assertTrue(expectedWorkingGroupsProject1.containsAll(InteractionService.getWorkingGroups("project1")));

        Assertions.assertEquals(expectedWorkingGroupsProject2.size(), InteractionService.getWorkingGroups("project2").size());
        Assertions.assertTrue(expectedWorkingGroupsProject2.containsAll(InteractionService.getWorkingGroups("project2")));
    }

}

