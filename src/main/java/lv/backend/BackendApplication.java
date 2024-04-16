package lv.backend;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public CommandLineRunner testModelLayer(IUserRepo userRepo, IParkingAreaRepo parkingAreaRepo,
			IParkingSpotRepo parkingSpotRepo, IReservationRepo reservationRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User us1 = new User(0, "Janis", "janis123", "000", "janisp@gmail.com", null);
				userRepo.save(us1);

			}

		};
	}
}

