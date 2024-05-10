package lv.backend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.backend.models.Reservation;
import lv.backend.utils.MyException;

public interface IReservationServices {
	
	Reservation retrieveReservationById(Long id) throws MyException;

	ArrayList<Reservation> selectAllReservations();

	Reservation createNewReservation(LocalDateTime startTime, LocalDateTime endTime );

	Reservation updateReservationById(Long id, LocalDateTime startTime, LocalDateTime endTime) throws MyException;

	void deleteReservationById(Long id) throws MyException;

	

}
