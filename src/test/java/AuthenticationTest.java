import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(Group.NONAUTHORIZED,UserService.authorize("NonAuthorized_user"));
        UserService.register("authorized_user", "qwerty");
        UserService.changeGroup("authorized_user", Group.USER);
        Assertions.assertEquals(Group.USER,UserService.authorize("authorized_user"));
        UserService.changeGroup("authorized_user", Group.ADMIN);
        Assertions.assertEquals(Group.ADMIN,UserService.authorize("authorized_user"));
    }
}
