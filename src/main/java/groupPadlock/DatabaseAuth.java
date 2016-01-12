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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

