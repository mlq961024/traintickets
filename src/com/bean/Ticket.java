package com.bean;

public class Ticket {
    private String id;
    private  String name;
    private String sate;
    private String trainid;
    private String userid;
    private String riderid;
    private String start;
    private String end;
    private String startt;
    private String endt;
    private double price;
    private int st;
    private int et;
    private int usering;
    private String date;
    private int satenum;
    private int buyed;

    public int getBuyed() {
        return buyed;
    }

    public void setBuyed(int buyed) {
        this.buyed = buyed;
    }

    public int getSatenum() {
        return satenum;
    }

    public void setSatenum(int satenum) {
        this.satenum = satenum;
    }

    public String getRiderid() {
        return riderid;
    }

    public void setRiderid(String riderid) {
        this.riderid = riderid;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSate() {
        return sate;
    }

    public void setSate(String sate) {
        this.sate = sate;
    }

    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public int getUsering() {
        return usering;
    }

    public void setUsering(int usering) {
        this.usering = usering;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sate='" + sate + '\'' +
                ", trainid='" + trainid + '\'' +
                ", userid='" + userid + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", startt='" + startt + '\'' +
                ", endt='" + endt + '\'' +
                ", price=" + price +
                ", st=" + st +
                ", et=" + et +
                ", usering=" + usering +
                ", date='" + date + '\'' +
                '}';
    }

    public void setDate(String date) {
        this.date = date;
    }
}
