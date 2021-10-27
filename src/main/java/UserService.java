
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UserService {
    private static final HashMap<Integer, User> users = new HashMap<>();
    private static int currentId;

    public static void clearBase() {
        users.clear();
    }


    private static User getUserByLoginThrowException(String login) {
        Optional<User> userOpt = getUserByLogin(login);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return userOpt.get();
    }

    private static Optional<User> getUserByLogin(String login) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    public static boolean register(@NotNull String login, @NotNull String password) {
        if (getUserByLogin(login).isPresent()) {
            return false;
        }
        User user = new User(login, password);
        users.put(currentId++, user);
        return true;
    }

    public static boolean authenticate(@NotNull String login, @NotNull String password) {
        Optional<User> user = getUserByLogin(login);
        return (user.isPresent() && Objects.equals(user.get().getPassword(), password));
    }

    public static Group authorize(@NotNull String login) {
        User user = getUserByLoginThrowException(login);
        return user.getGroup();
    }

    public static void changeGroup(@NotNull String login, Group group) {
        User user = getUserByLoginThrowException(login);
        user.setGroup(group);
    }

    public static void addWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        User user = getUserByLoginThrowException(login);
        user.addWorkingGroup(workingGroup);
    }

    public static boolean userInWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        User user = getUserByLoginThrowException(login);
        return user.isInWorkingGroup(workingGroup);
    }

    public static boolean removeFromWorkingGroup(@NotNull String login, @NotNull String workingGroup) {
        User user = getUserByLoginThrowException(login);
        return user.removeWorkingGroup(workingGroup);
    }

    public static List<String> getUsersFromWorkingGroup(@NotNull String workingGroup) {
        List<String> usersInWorkingGroup = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user.getWorkingGroups().contains(workingGroup)) {
                usersInWorkingGroup.add(user.getLogin());
            }
        }
        return usersInWorkingGroup;
    }

    public static List<String> getWorkingGroups(@NotNull String login) {
        User user = getUserByLoginThrowException(login);
        return new ArrayList<>(user.getWorkingGroups());
    }

    public static boolean changePassword(@NotNull String login, @NotNull String oldPassword, @NotNull String newPassword) {
        User user = getUserByLoginThrowException(login);
        if (!authenticate(login, oldPassword) || Objects.equals(oldPassword, newPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        return true;
    }

    public static boolean changeLogin(@NotNull String oldLogin, @NotNull String password, @NotNull String newLogin) {
        User user = getUserByLoginThrowException(oldLogin);
        if (!authenticate(oldLogin, password) || Objects.equals(oldLogin, newLogin)) {
            return false;
        }
        Optional<User> secondUser = getUserByLogin(newLogin);
        if (secondUser.isPresent()) {
            return false;
        }
        user.setLogin(newLogin);
        return true;
    }
}

