package com.example.kursach_3.Tables;

public class User_table
{
    private int idUser,idEmployee;
    String password;

    public User_table(int idUser, int idEmployee, String password) {
        this.idUser = idUser;
        this.idEmployee = idEmployee;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
