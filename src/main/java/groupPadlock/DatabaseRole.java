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
}
