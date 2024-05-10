package lv.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "parking_lot_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ParkingLot {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idl")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idl;

	@Column(name = "TotalSpots")
	private int totalSpots;

}
