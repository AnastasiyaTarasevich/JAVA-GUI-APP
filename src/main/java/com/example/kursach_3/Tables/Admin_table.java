package com.example.kursach_3.Tables;

public class Admin_table
{
    int id;
    String login,passw;

    public Admin_table(int id, String login, String passw) {
        this.id = id;
        this.login = login;
        this.passw = passw;
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

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
}
