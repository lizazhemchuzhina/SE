import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationTest {

    @Test
    public void userAuthentication() {
        UserService.register("user1", "password");
        Assertions.assertTrue(AuthenticationService.authenticate("user1", "password"));
        Assertions.assertFalse(AuthenticationService.authenticate("user2", "password"));
    }
}
