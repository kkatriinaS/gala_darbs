package lv.backend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.backend.models.ParkingSpot;
import lv.backend.models.Reservation;
import lv.backend.models.users.User;
import lv.backend.utils.MyException;

public interface IReservationServices {
	
	Reservation retrieveReservationById(Long id) throws MyException;

	ArrayList<Reservation> selectAllReservations();

	Reservation updateReservationById(Long id, LocalDateTime startTime, LocalDateTime endTime) throws MyException;

	void deleteReservationById(Long id) throws MyException;

	Reservation createNewReservation(LocalDateTime startTime, LocalDateTime endTime, User user,
			ParkingSpot parkingSpot);

	

}
