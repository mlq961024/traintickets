package com.bean;

public class User {
    private String id;
    private String password;
    private double balance;
    private int power;
    private String sex;
    private String birthday;
    private String psq;
    private String answer;
    private String inform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPsq() {
        return psq;
    }

    public void setPsq(String psq) {
        this.psq = psq;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getInform() {
        return inform;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", power=" + power +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", psq='" + psq + '\'' +
                ", answer='" + answer + '\'' +
                ", inform='" + inform + '\'' +
                '}';
    }
}
