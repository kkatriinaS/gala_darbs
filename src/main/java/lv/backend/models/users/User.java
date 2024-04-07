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

	// One-to-many saite ar Reservations
	@OneToMany(mappedBy = "user")
	private Collection<Reservation> reservations;

	public User(long idu, String name, String username, String password, String email,
			Collection<Reservation> reservations) {
		super();
		this.idu = idu;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "User [idu=" + idu + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", reservations=" + reservations + "]";
	}

}
	
	
	