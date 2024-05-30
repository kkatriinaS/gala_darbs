package lv.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.models.Reservation;
import lv.backend.models.SpotStatus;
import lv.backend.repos.IParkingSpotRepo;
import lv.backend.services.IParkingSpotServices;
import lv.backend.utils.MyException;

@Service
public class ParkingSpotServicesImplementation implements IParkingSpotServices {

	@Autowired
	private IParkingSpotRepo parkingSpotRepo;

	public ParkingSpot createNewParkingSpot(Long id, SpotStatus spotStatus, Reservation reservation,
			ParkingArea parkingArea) {
		return parkingSpotRepo.save(new ParkingSpot(id, spotStatus, reservation, parkingArea));
	}

	public ParkingSpot retrieveParkingSpotById(Long id) throws MyException {
		if (parkingSpotRepo.existsById(id)) {
			return parkingSpotRepo.findById(id).get();
		} else {
			throw new MyException("Wrong id");
		}
	}

	public ArrayList<ParkingSpot> retrieveAllParkingSpots() {
		return (ArrayList<ParkingSpot>) parkingSpotRepo.findAll();
	}

	public ParkingSpot updateParkingSpotById(Long id, SpotStatus spotStatus, Reservation reservation,
			ParkingArea parkingArea) throws MyException {
		if (parkingSpotRepo.existsById(id)) {
			ParkingSpot updatedParkingSpot = parkingSpotRepo.findById(id).get();
			updatedParkingSpot.setSpotStatus(spotStatus);
			updatedParkingSpot.setReservation(reservation);
			updatedParkingSpot.setParkingArea(parkingArea);
			return parkingSpotRepo.save(updatedParkingSpot);

		} else {
			throw new MyException("Wrong id");
		}
	}

	public void deleteParkingSpotById(Long id) throws MyException {
		if (parkingSpotRepo.existsById(id)) {
			parkingSpotRepo.deleteById(id);
		} else {
			throw new MyException("Wrong id");
		}

	}

	@Override
	public ArrayList<ParkingSpot> selectAllParkingSpots() {
		return (ArrayList<ParkingSpot>) parkingSpotRepo.findAll();
		
	}

	@Override
	public ParkingSpot createNewParkingSpot(Enum<SpotStatus> spotStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkingSpot updateParkingSpotById(Long id, Enum<SpotStatus> spotStatus) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
