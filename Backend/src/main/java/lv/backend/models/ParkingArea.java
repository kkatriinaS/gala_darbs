package lv.backend.models;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "parking_area_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParkingArea {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Ida")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ida;

	@Column(name = "Name")
	private String areaName;

	@Column(name = "TotalSpots")
	private int totalSpots;

	// One-to-many saite ar ParkingSpots
	//@OneToMany(mappedBy = "parkingArea")
	//private Collection<ParkingSpot> parkingSpots;

	@OneToMany(mappedBy = "parkingArea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpot> parkingSpots;
	
	public ParkingArea(long ida, String areaName, int totalSpots, Collection<ParkingSpot> parkingSpots) {
		super();
		this.ida = ida;
		this.areaName = areaName;
		this.totalSpots = totalSpots;
		this.parkingSpots = (List<ParkingSpot>) parkingSpots;
	}

	@Override
	public String toString() {
		return "ParkingArea [ida=" + ida + ", areaName=" + areaName + ", totalSpots=" + totalSpots + ", parkingSpots="
				+ parkingSpots + "]";
	}

}
