package com.example.kursach_3.Tables;

public class Pay_table
{
    int idPay;
    int idEmp;
    int idPrem;
    String period;
    int hours;
    double prepayment;
    String income_tax;
    String social_tax;
    String pretax_sum;
    String total_sum;

    public Pay_table(int idPay, int idEmp, int idPrem, String period, int hours, double prepayment, String income_tax, String social_tax, String pretax_sum, String total_sum) {
        this.idPay = idPay;
        this.idEmp = idEmp;
        this.idPrem = idPrem;
        this.period = period;
        this.hours = hours;
        this.prepayment = prepayment;
        this.income_tax = income_tax;
        this.social_tax = social_tax;
        this.pretax_sum = pretax_sum;
        this.total_sum = total_sum;
    }

    public int getIdPay() {
        return idPay;
    }

    public void setIdPay(int idPay) {
        this.idPay = idPay;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdPrem() {
        return idPrem;
    }

    public void setIdPrem(int idPrem) {
        this.idPrem = idPrem;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(double prepayment) {
        this.prepayment = prepayment;
    }

    public String getIncome_tax() {
        return income_tax;
    }

    public void setIncome_tax(String income_tax) {
        this.income_tax = income_tax;
    }

    public String getSocial_tax() {
        return social_tax;
    }

    public void setSocial_tax(String social_tax) {
        this.social_tax = social_tax;
    }

    public String getPretax_sum() {
        return pretax_sum;
    }

    public void setPretax_sum(String pretax_sum) {
        this.pretax_sum = pretax_sum;
    }

    public String getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(String total_sum) {
        this.total_sum = total_sum;
    }
}
