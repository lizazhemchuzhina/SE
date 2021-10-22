public class User {
    private String login;
    private String password;
    private Group group;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.group = Group.NONAUTHORIZED;
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