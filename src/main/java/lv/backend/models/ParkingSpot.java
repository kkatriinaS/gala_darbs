package lv.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Table(name = "parking_spot_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParkingSpot {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Ids")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ids;

	@Column(name = "SpotStatus")
	@Enumerated(EnumType.STRING)
	private SpotStatus spotStatus;

	// One-to-one saite ar Reservation
	@OneToOne(mappedBy = "parkingSpot")
	private Reservation reservation;

	// Many-to-one saite ar ParkingArea
	@ManyToOne
	@JoinColumn(name = "parking_area_id")
	private ParkingArea parkingArea;

	public ParkingSpot(long ids, SpotStatus spotStatus, Reservation reservation, ParkingArea parkingArea) {
		super();
		this.ids = ids;
		this.spotStatus = spotStatus;
		this.reservation = reservation;
		this.parkingArea = parkingArea;
	}

	@Override
	public String toString() {
		return "ParkingSpot [ids=" + ids + ", spotStatus=" + spotStatus + ", reservation=" + reservation
				+ ", parkingArea=" + parkingArea + "]";
	}

}
