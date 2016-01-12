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
}
