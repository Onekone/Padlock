package groupPadlock;

import java.time.LocalDate;

/**
 * Created by Onekone on 13.01.2016.
 */
public class DatabaseAuth {
    String login;
    String name;
    String hash;
    String salt;
    public DatabaseAuth(String login, String name, String hash, String salt) {
        this.login = login;
        this.name = name;
        this.hash = hash;
        this.salt = salt;
    }
}

