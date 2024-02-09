package com.example.kursach_3.Tables;

public class Emp_table
{
    int idEmp;
    int idPos;
    String surname,name,secname;

    public Emp_table(int idEmp, int idPos, String surname, String name, String secname)
    {
        this.idEmp = idEmp;
        this.idPos = idPos;
        this.surname = surname;
        this.name = name;
        this.secname = secname;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdPos() {
        return idPos;
    }

    public void setIdPos(int idPos) {
        this.idPos = idPos;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }
}
