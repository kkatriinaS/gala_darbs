package com.example.parkingapp.model;

public class Reservation {
    private long idu;
    private String startTime;
    private String endTime;
    private long userId;
    private long parkingSpotId;

    // Default constructor
    public Reservation() {}

    // Getters and Setters
    public long getIdu() {
        return idu;
    }

    public void setIdu(long idu) {
        this.idu = idu;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(long parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }
}


