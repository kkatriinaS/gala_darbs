package lv.backend.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.repos.IParkingAreaRepo;
import lv.backend.services.IParkingAreaServices;
import lv.backend.utils.MyException;

@Service
public class ParkingAreaServicesImplementation implements IParkingAreaServices{

	@Autowired
	private IParkingAreaRepo parkingAreaRepo;

	public ParkingArea createNewParkingArea(String name, int totalSpots, ParkingSpot parkingSpots) {
		return parkingAreaRepo.save(new ParkingArea(totalSpots, name, totalSpots, null));
	}

	public ParkingArea retrieveParkingAreaById(Long id) throws MyException {
		if (parkingAreaRepo.existsById(id)) {
			return parkingAreaRepo.findById(id).get();
		} else {
			throw new MyException("Wrong id");
		}
	}

	public ArrayList<ParkingArea> retrieveAllParkingAreas() {
		return (ArrayList<ParkingArea>) parkingAreaRepo.findAll();
	}

	public ParkingArea updateParkingAreaById(Long id, String name, int totalSpots) throws MyException {
		if (parkingAreaRepo.existsById(id)) {
			ParkingArea updatedParkingArea = parkingAreaRepo.findById(id).get();
			updatedParkingArea.setAreaName(name);
			updatedParkingArea.setTotalSpots(totalSpots);
			return parkingAreaRepo.save(updatedParkingArea);

		} else {
			throw new MyException("Wrong id");
		}
	}

	public void deleteParkingAreaById(Long id) throws MyException {
		if (parkingAreaRepo.existsById(id)) {
			parkingAreaRepo.deleteById(id);
		} else {
			throw new MyException("Wrong id");
		}

	}

	@Override
	public ArrayList<ParkingArea> selectAllParkingArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkingArea createNewParkingArea(String name, int totalSpots) {
		// TODO Auto-generated method stub
		return null;
	}
}
