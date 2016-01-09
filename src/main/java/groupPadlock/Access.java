package groupPadlock;

/**
 * Created by Onekone on 09.01.2016.
 */
public class Access {
    public int role;
    public String path;
    public String login;
    public Access(String login,String path,int role)
    {
        this.role = role;
        this.path = path;
        this.login = login;
    }
}
