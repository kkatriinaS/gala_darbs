package lv.backend.services;

import java.util.ArrayList;


import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.utils.MyException;


public interface IParkingAreaServices {

	ParkingArea retrieveParkingAreaById(Long id) throws MyException;

	ArrayList<ParkingArea> selectAllParkingArea();

	//ParkingArea createNewParkingArea(String name, int totalSpots);

	ParkingArea updateParkingAreaById(Long id, String name, int totalSpots) throws MyException;

	void deleteParkingAreaById(Long id) throws MyException;

	ParkingArea createNewParkingArea(String name, int totalSpots, ParkingSpot parkingSpots);

	ArrayList<ParkingArea> retrieveAllParkingAreas();

	

}
