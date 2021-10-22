import java.util.*;

import org.jetbrains.annotations.NotNull;

public class UserService {
    static private final HashMap<Integer, User> users = new HashMap<>();
    static private int currentId = 0;

    static private User getUserByLoginThrowException(String login) {
        Optional<User> userOpt = getUserByLogin(login);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return userOpt.get();
    }

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
        User user = getUserByLoginThrowException(login);
        return user.getGroup();
    }

    static public void changeGroup(@NotNull String login, Group group) {
        User user = getUserByLoginThrowException(login);
        user.setGroup(group);
    }

    static public void addWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        User user = getUserByLoginThrowException(login);
        user.addWorkingGroup(workingGroup);
    }

    static public boolean userInWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        User user = getUserByLoginThrowException(login);
        return user.isInWorkingGroup(workingGroup);
    }

    static public boolean removeFromWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        User user = getUserByLoginThrowException(login);
        return user.removeWorkingGroup(workingGroup);
    }

    static public List<String> getUsersFromWorkingGroup(@NotNull String workingGroup) {
        List<String> usersInWorkingGroup = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user.getWorkingGroups().contains(workingGroup)) {
                usersInWorkingGroup.add(user.getLogin());
            }
        }
        return usersInWorkingGroup;
    }
}
