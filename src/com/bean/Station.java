package com.bean;

public class Station {
    private String city;
    private String province;

    public Station() {
    }

    public Station(String city, String province) {
        this.city = city;
        this.province = province;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
