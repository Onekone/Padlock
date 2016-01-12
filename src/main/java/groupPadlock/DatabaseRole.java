package groupPadlock;

public class DatabaseRole
{
    int id;
    String login;
    int role;
    String path;
    public DatabaseRole(int id, String login, int role, String path) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
