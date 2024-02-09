package com.example.kursach_3;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public  class Accountant
{

    private int id;
    private String login;
    private String password;
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }
}
