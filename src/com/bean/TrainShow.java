package com.bean;

public class TrainShow {
    private String id;
    private String start;
    private String end;
    private String date;
    private String startt;
    private String endt;
    private double price;
    private int timenum;
    private int num;
    private int nowt;
    private String ago;

    @Override
    public String toString() {
        return "TrainShow{" +
                "id='" + id + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", date='" + date + '\'' +
                ", startt='" + startt + '\'' +
                ", endt='" + endt + '\'' +
                ", price=" + price +
                ", timenum=" + timenum +
                ", num=" + num +
                ", nowt=" + nowt +
                ", ago='" + ago + '\'' +
                '}';
    }

    public int getTimenum() {
        return timenum;
    }

    public void setTimenum(int timenum) {
        this.timenum = timenum;
    }

    public int getNowt() {
        return nowt;
    }

    public void setNowt(int nowt) {
        this.nowt = nowt;
    }

    public String getAgo() {
        return ago;
    }

    public void setAgo(String ago) {
        this.ago = ago;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
