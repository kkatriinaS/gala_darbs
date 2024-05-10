package com.example.parkingapp.model;

import java.util.Collection;


public class ParkingArea {


	private long ida;
	private String areaName;
	private int totalSpots;

	public long getIda() {
		return ida;
	}

	public String getAreaName() {
		return areaName;
	}

	public int getTotalSpots() {
		return totalSpots;
	}

	public void setIda(long ida) {
		this.ida = ida;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setTotalSpots(int totalSpots) {
		this.totalSpots = totalSpots;
	}

	public ParkingArea() {
	}

	public ParkingArea(long ida, String areaName, int totalSpots) {
		super();
		this.ida = ida;
		this.areaName = areaName;
		this.totalSpots = totalSpots;
	}

	@Override
	public String toString() {
		return "ParkingArea [ida=" + ida + ", areaName=" + areaName + ", totalSpots=" + totalSpots + "]";
	}

}
