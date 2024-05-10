package lv.backend;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.models.Reservation;
import lv.backend.models.SpotStatus;
import lv.backend.models.users.User;
import lv.backend.repos.IParkingAreaRepo;
import lv.backend.repos.IParkingSpotRepo;
import lv.backend.repos.IReservationRepo;
import lv.backend.repos.IUserRepo;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner testModelLayer(IUserRepo userRepo, IParkingAreaRepo parkingAreaRepo,
			IParkingSpotRepo parkingSpotRepo, IReservationRepo reservationRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User us1 = new User(0, "Janis", "janis123", "000", "janisp@gmail.com", null);
				User us2 = new User(0, "Ieva", "iozola", "1188", "ievao@gmail.com", null);
				userRepo.save(us1);
				userRepo.save(us2);
				
				ParkingArea pa1 = new ParkingArea(0, "Arēna Rīga", 1000, null);
				ParkingArea pa2 = new ParkingArea(0, "Latgales Vēstniecība GORS", 150, null);
				parkingAreaRepo.save(pa1);
				parkingAreaRepo.save(pa2);
				
				ParkingSpot ps1 = new ParkingSpot(0, SpotStatus.FREE, null, pa1);
				ParkingSpot ps2 = new ParkingSpot(0, SpotStatus.FREE, null, pa1);
				ParkingSpot ps3 = new ParkingSpot(0, SpotStatus.TAKEN, null, pa1);
				ParkingSpot ps4 = new ParkingSpot(0, SpotStatus.FREE, null, pa1);
				ParkingSpot ps5 = new ParkingSpot(0, SpotStatus.TAKEN, null, pa2);
				ParkingSpot ps6 = new ParkingSpot(0, SpotStatus.FREE, null, pa2);
				parkingSpotRepo.save(ps1);
				parkingSpotRepo.save(ps2);
				parkingSpotRepo.save(ps3);
				parkingSpotRepo.save(ps4);
				parkingSpotRepo.save(ps5);
				parkingSpotRepo.save(ps6);
				
				Reservation res1 = new Reservation(0, null, null, us2, ps5);
				reservationRepo.save(res1);

			}

		};
	}
}

