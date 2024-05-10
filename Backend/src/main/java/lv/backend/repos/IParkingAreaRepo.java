package lv.backend.repos;

import org.springframework.data.repository.CrudRepository;

import lv.backend.models.ParkingArea;

public interface IParkingAreaRepo extends CrudRepository <ParkingArea, Long> {

}
