import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthenticationTest {

    @Test
    public void userAuthentication() {
        Assertions.assertTrue(UserService.register("user1", "password"));
        Assertions.assertFalse(UserService.register("user1", "password"));
        Assertions.assertTrue(UserService.authenticate("user1", "password"));
        Assertions.assertFalse(UserService.authenticate("user2", "password"));
    }

    @Test
    public void userAuthorization() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserService.authorize("NonAuthorized_user"),
                "Expected illegal argument exception but it was not thrown");
        UserService.register("NonAuthorized_user", "120912192109");
        Assertions.assertEquals(Group.NONAUTHORIZED, UserService.authorize("NonAuthorized_user"));
        UserService.register("authorized_user", "qwerty");
        UserService.changeGroup("authorized_user", Group.USER);
        Assertions.assertEquals(Group.USER, UserService.authorize("authorized_user"));
        UserService.changeGroup("authorized_user", Group.ADMIN);
        Assertions.assertEquals(Group.ADMIN, UserService.authorize("authorized_user"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> UserService.changeGroup("NonExistent_user", Group.USER),
                "Expected illegal argument exception but it was not thrown");
    }

    @Test
    public void userWorkingGroups() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> UserService.userInWorkingGroup("NonExistent_user", "some_group"),
                "Expected illegal argument exception but it was not thrown");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> UserService.addWorkingGroup("NonExistent_user", "some_group"),
                "Expected illegal argument exception but it was not thrown");
        UserService.register("authorized_user", "qwerty");
        UserService.addWorkingGroup("authorized_user", "working_group1");
        UserService.addWorkingGroup("authorized_user", "working_group2");
        Assertions.assertTrue(UserService.userInWorkingGroup("authorized_user", "working_group1"));
        Assertions.assertTrue(UserService.userInWorkingGroup("authorized_user", "working_group2"));
        Assertions.assertFalse(UserService.userInWorkingGroup("authorized_user", "working_group3"));
    }

    @Test
    public void userRemoveFromWorkingGroup() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> UserService.removeFromWorkingGroup("user", "some_group"),
                "Expected illegal argument exception but it was not thrown");
        UserService.register("user", "password");
        UserService.addWorkingGroup("user", "working_group1");
        UserService.addWorkingGroup("user", "working_group2");
        Assertions.assertFalse(UserService.removeFromWorkingGroup("user", "working_group3"));
        Assertions.assertTrue(UserService.removeFromWorkingGroup("user", "working_group1"));
        Assertions.assertFalse(UserService.userInWorkingGroup("user", "working_group1"));
        Assertions.assertTrue(UserService.userInWorkingGroup("user", "working_group2"));
    }

    @Test
    public void whoIsInWorkingGroup() {
        UserService.register("Lena", "qwerty");
        UserService.addWorkingGroup("Lena", "working_group1");
        UserService.addWorkingGroup("Lena", "working_group3");

        UserService.register("Liza", "qwerty");
        UserService.addWorkingGroup("Liza", "working_group1");

        UserService.register("Vlad", "qwerty");
        UserService.addWorkingGroup("Vlad", "working_group1");

        UserService.register("Tankov", "qwerty");
        UserService.addWorkingGroup("Tankov", "working_group1");

        List<String> expectedUsersInGroup = new ArrayList<>(Arrays.asList("Lena", "Liza", "Vlad", "Tankov"));
        List<String> actualUsersInGroup = UserService.getUsersFromWorkingGroup("working_group1");
        Assertions.assertEquals(expectedUsersInGroup.size(), actualUsersInGroup.size());
        Assertions.assertTrue(expectedUsersInGroup.contains(actualUsersInGroup));
        Assertions.assertTrue(actualUsersInGroup.contains(expectedUsersInGroup));

        List<String> actualUsersInGroupEmpty = UserService.getUsersFromWorkingGroup("working_group2");
        Assertions.assertEquals(0, actualUsersInGroupEmpty.size());

        List<String> actualUsersInGroupSingle = UserService.getUsersFromWorkingGroup("working_group3");
        Assertions.assertEquals(1, actualUsersInGroupSingle.size());
        Assertions.assertTrue(actualUsersInGroup.contains("Lena"));
    }

}
