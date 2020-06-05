package com.bean;

public class Rider {
    private String riderid;
    private String ridername;

    public String getRiderid() {
        return riderid;
    }

    public void setRiderid(String riderid) {
        this.riderid = riderid;
    }

    public String getRidername() {
        return ridername;
    }

    public void setRidername(String ridername) {
        this.ridername = ridername;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "riderid='" + riderid + '\'' +
                ", ridername='" + ridername + '\'' +
                '}';
    }
}
