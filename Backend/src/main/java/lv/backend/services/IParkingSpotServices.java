package lv.backend.services;

import java.util.ArrayList;
import java.util.List;

import lv.backend.models.ParkingSpot;
import lv.backend.models.SpotStatus;
import lv.backend.utils.MyException;

public interface IParkingSpotServices {
	ParkingSpot retrieveParkingSpotById(Long id) throws MyException;

	ArrayList<ParkingSpot> selectAllParkingSpots();

	ParkingSpot createNewParkingSpot(Enum<SpotStatus> spotStatus);

	ParkingSpot updateParkingSpotById(Long id, Enum<SpotStatus> spotStatus) throws MyException;

	void deleteParkingSpotById(Long id) throws MyException;


}
