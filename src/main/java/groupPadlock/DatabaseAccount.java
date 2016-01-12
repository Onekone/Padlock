package groupPadlock;

import java.time.LocalDate;

public class DatabaseAccount
{
    int id;
    String login;
    java.time.LocalDate date_start;
    java.time.LocalDate date_end;
    int role;
    public DatabaseAccount(int id, String login, LocalDate date_start, LocalDate date_end, int role) {
        this.id = id;
        this.login = login;
        this.date_start = date_start;
        this.date_end = date_end;
        this.role = role;
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

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
