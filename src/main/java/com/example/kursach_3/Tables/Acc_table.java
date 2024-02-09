package com.example.kursach_3.Tables;

public class Acc_table {


    private int id;
    private String login;
    private String password;

    public Acc_table(int id, String login, String password) {

        this.id=id;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
