package com.example.parkingapp.model;

public class ParkingSpot {
    private long ids;
    private String spotStatus;

    public ParkingSpot(long ids, String spotStatus) {
        this.ids = ids;
        this.spotStatus = spotStatus;
    }

    public ParkingSpot() {

    }

    public long getIds() {
        return ids;
    }

    public void setIds(long ids) {
        this.ids = ids;
    }

    public String getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(String spotStatus) {
        this.spotStatus = spotStatus;
    }
}

