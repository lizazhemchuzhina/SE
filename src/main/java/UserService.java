import java.util.HashMap;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class UserService {
    static private HashMap<String, String> users = new HashMap<>();

    static public boolean register(@NotNull String login, @NotNull String password) {
        if (users.containsKey(login)) {
            return false;
        }
        users.put(login, password);
        return true;
    }

    static public boolean authenticate(@NotNull String login, @NotNull String password) {
        return Objects.equals(users.get(login), password);
    }

}
