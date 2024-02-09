package com.example.kursach_3.Tables;

public class Prem_table
{
    int id;
    String name;
    double sum;

    public Prem_table(int id, String name, double sum) {
        this.id = id;
        this.name = name;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
