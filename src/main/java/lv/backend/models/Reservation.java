package lv.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Setter;
import lv.backend.models.users.User;

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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	//TODO pievienot one-to-one saiti ar parking spot
	
	public Reservation(long idr, LocalDateTime startTime, LocalDateTime endTime, User user) {
		super();
		this.idr = idr;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reservation [idr=" + idr + ", startTime=" + startTime + ", endTime=" + endTime + ", user=" + user + "]";
	}

}
