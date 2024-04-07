package lv.backend.repos;

import org.springframework.data.repository.CrudRepository;

import lv.backend.models.Reservation;

public interface IReservationRepo extends CrudRepository <Reservation, Long>{

}
