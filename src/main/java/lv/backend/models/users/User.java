package lv.backend.models.users;

import java.util.Collection;

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
import lv.backend.models.Reservation;


@Table(name = "user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idu;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Username")
	private String username; 
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Email")
	private String email;

	public User(String name, String username, String password, String email) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	@OneToMany(mappedBy = "user")
    private Collection<Reservation> reservations;
	
}
	
	
	