package lv.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
		
		//TODO many-to-one saite ar parking lot
		
		
		public ParkingSpot(long idc, SpotStatus spotStatus) {
			super();
			this.ids = ids;
			this.spotStatus = spotStatus;
		}

		@Override
		public String toString() {
			return "ParkingSpot [ids=" + ids + ", spotStatus=" + spotStatus + "]";
		}

	}

