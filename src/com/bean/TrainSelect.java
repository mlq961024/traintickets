package com.bean;

import java.util.List;

public class TrainSelect {
    private String id;
    private String start;
    private String end;
    private String date;
    private String startt;
    private String endt;
    private double price;
    private int num;
    private int st;
    private int et;
    private int nowt;
    private List<String> ago;

    public List<String> getAgo() {
        return ago;
    }

    public void setAgo(List<String> ago) {
        this.ago = ago;
    }

    public int getNowt() {
        return nowt;
    }

    public void setNowt(int nowt) {
        this.nowt = nowt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartt() {
        return startt;
    }

    public void setStartt(String startt) {
        this.startt = startt;
    }

    public String getEndt() {
        return endt;
    }

    public void setEndt(String endt) {
        this.endt = endt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getEt() {
        return et;
    }

    public void setEt(int et) {
        this.et = et;
    }

    @Override
    public String toString() {
        return "TrainSelect{" +
                "id='" + id + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", date='" + date + '\'' +
                ", startt='" + startt + '\'' +
                ", endt='" + endt + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", st=" + st +
                ", et=" + et +
                ", nowt=" + nowt +
                ", ago=" + ago +
                '}';
    }
}
