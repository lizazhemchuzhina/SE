import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTest {

    @Test
    public void userAuthentication() {
        UserService.register("user1", "password");
        Assertions.assertTrue(UserService.authenticate("user1", "password"));
        Assertions.assertFalse(UserService.authenticate("user2", "password"));
    }
}
