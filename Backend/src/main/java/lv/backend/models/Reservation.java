package lv.backend.models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.backend.models.users.User;

@Table(name = "reservation_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reservation {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idr;
	
	@Column(name = "StartTime")
	private LocalDateTime startTime;
	
	@Column(name = "EndTime")
	private LocalDateTime endTime; 
	
	// Many-to-one saite ar User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// One-to-one saite ar ParkingSpot
	@OneToOne
	@JoinColumn(name = "parking_spot_id") 
	private ParkingSpot parkingSpot;

	public Reservation(long idr, LocalDateTime startTime, LocalDateTime endTime, User user, ParkingSpot parkingSpot) {
		super();
		this.idr = idr;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
		this.parkingSpot = parkingSpot;
	}

	@Override
	public String toString() {
		return "Reservation [idr=" + idr + ", startTime=" + startTime + ", endTime=" + endTime + ", user=" + user
				+ ", parkingSpot=" + parkingSpot + "]";
	}
	
}
