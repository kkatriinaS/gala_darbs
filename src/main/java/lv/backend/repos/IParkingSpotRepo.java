package lv.backend.repos;

import org.springframework.data.repository.CrudRepository;

import lv.backend.models.ParkingSpot;

public interface IParkingSpotRepo extends CrudRepository <ParkingSpot, Long> {

}
