package lv.backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.backend.models.ParkingSpot;
import lv.backend.models.Reservation;
import lv.backend.models.users.User;
import lv.backend.repos.IReservationRepo;
import lv.backend.services.IReservationServices;
import lv.backend.utils.MyException;

@Service
public class ReservationServicesImplementation implements IReservationServices {

	@Autowired
	private IReservationRepo reservationRepo;

	public Reservation createNewReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, User user,
			ParkingSpot parkingSpot) {
		return reservationRepo.save(new Reservation(id, endTime, endTime, user, parkingSpot));
	}

	public Reservation retrieveReservationById(Long id) throws MyException {
		if (reservationRepo.existsById(id)) {
			return reservationRepo.findById(id).get();
		} else {
			throw new MyException("Wrong id");
		}
	}

	public ArrayList<Reservation> retrieveAllReservations() {
		return (ArrayList<Reservation>) reservationRepo.findAll();
	}

	public Reservation updateReservationById(Long id, LocalDateTime startTime, LocalDateTime endTime, User user,
			ParkingSpot parkingSpot) throws MyException {
		if (reservationRepo.existsById(id)) {
			Reservation updatedReservation = reservationRepo.findById(id).get();
			updatedReservation.setStartTime(startTime);
			updatedReservation.setEndTime(endTime);
			updatedReservation.setUser(user);
			updatedReservation.setParkingSpot(parkingSpot);
			return reservationRepo.save(updatedReservation);

		} else {
			throw new MyException("Wrong id");
		}
	}

	public void deleteReservationById(Long id) throws MyException {
		if (reservationRepo.existsById(id)) {
			reservationRepo.deleteById(id);
		} else {
			throw new MyException("Wrong id");
		}

	}

	@Override
	public ArrayList<Reservation> selectAllReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createNewReservation(LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation updateReservationById(Long id, LocalDateTime startTime, LocalDateTime endTime)
			throws MyException {
		// TODO Auto-generated method stub
		return null;
	}
}
