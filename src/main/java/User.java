import javax.print.attribute.HashAttributeSet;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String login;
    private String password;
    private Group group;
    private Set<String> workingGroups;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.group = Group.NONAUTHORIZED;
        this.workingGroups = new HashSet<>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Group getGroup() {
        return group;
    }

    public Set<String> getWorkingGroups() {
        return workingGroups;
    }

    public void addWorkingGroup(String newGroup) {
        workingGroups.add(newGroup);
    }

    public boolean isInWorkingGroup(String workingGroup) {
        return workingGroups.contains(workingGroup);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}