import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

public class UserService {
    static private final HashMap<Integer, User> users = new HashMap<>();
    static private int currentId = 0;

    static private Optional<User> getUserByLogin(String login) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    static public boolean register(@NotNull String login, @NotNull String password) {
        if (getUserByLogin(login).isPresent()) {
            return false;
        }
        User user = new User(login, password);
        users.put(currentId++, user);
        return true;
    }

    static public boolean authenticate(@NotNull String login, @NotNull String password) {
        Optional<User> user = getUserByLogin(login);
        return (user.isPresent() && Objects.equals(user.get().getPassword(), password));
    }

    static public Group authorize(@NotNull String login) {
        Optional<User> user = getUserByLogin(login);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return user.get().getGroup();
    }

    static public void changeGroup(@NotNull String login, Group group) {
        Optional<User> user = getUserByLogin(login);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        user.get().setGroup(group);
    }

    static public void addWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        Optional<User> user = getUserByLogin(login);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        user.get().addWorkingGroup(workingGroup);
    }

    static public boolean userInWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        Optional<User> user = getUserByLogin(login);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return user.get().isInWorkingGroup(workingGroup);
    }
}
