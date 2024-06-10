package lv.backend;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.backend.models.ParkingArea;
import lv.backend.models.ParkingSpot;
import lv.backend.models.Reservation;
import lv.backend.models.SpotStatus;
import lv.backend.models.users.User;
import lv.backend.repos.IParkingAreaRepo;
import lv.backend.repos.IParkingSpotRepo;
import lv.backend.repos.IReservationRepo;
import lv.backend.repos.IUserRepo;
import lv.backend.confs.SecurityConfig;
import lv.backend.models.users.Authorities;
import lv.backend.repos.IAuthorityRepo;


@SpringBootApplication(scanBasePackages = { "lv.backend"})
@Import({ lv.backend.confs.SecurityConfig.class })
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoderSimple() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner testModelLayer(final IUserRepo userRepo, final IParkingAreaRepo parkingAreaRepo,
			final IParkingSpotRepo parkingSpotRepo, final IReservationRepo reservationRepo,final IAuthorityRepo authorityRepo, PasswordEncoder passwordEncoderSimple) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User us1 = new User( "Janis", "janis123", passwordEncoderSimple.encode("000"), "janisp@gmail.com", null);
				User us2 = new User( "Ieva", "iozola", passwordEncoderSimple.encode("1188"), "ievao@gmail.com", null);
				
				userRepo.save(us1);
				userRepo.save(us2);
			
				
				Authorities auth1 = new Authorities("ADMIN");
				Authorities auth2 = new Authorities("USER");

				auth1.addUser(us1);
				auth2.addUser(us1);

				authorityRepo.save(auth1);
				authorityRepo.save(auth2);

				us1.addAuthority(auth1);
				us1.addAuthority(auth2);
				
				 userRepo.save(us1);
		         userRepo.save(us2);
			
				
		
				
				ParkingArea pa1 = new ParkingArea(0, "Arēna Rīga", 1000, null);
				ParkingArea pa2 = new ParkingArea(0, "Latgales Vēstniecība GORS", 150, null);
				ParkingArea pa3 = new ParkingArea(0, "VeA", 200, null);
				parkingAreaRepo.save(pa1);
				parkingAreaRepo.save(pa2);
				parkingAreaRepo.save(pa3);
				
				ParkingSpot ps1 = new ParkingSpot(0, SpotStatus.FREE, null, pa1);
				ParkingSpot ps2 = new ParkingSpot(0, SpotStatus.FREE, null, pa1);
				ParkingSpot ps3 = new ParkingSpot(0, SpotStatus.TAKEN, null, pa1);
				ParkingSpot ps4 = new ParkingSpot(0, SpotStatus.FREE, null, pa1);
				
				parkingSpotRepo.save(ps1);
				parkingSpotRepo.save(ps2);
				parkingSpotRepo.save(ps3);
				parkingSpotRepo.save(ps4);
				
				
				Reservation res1 = new Reservation( 0, null, null, us2, ps4);
				reservationRepo.save(res1);

			}

		};
	}
}

