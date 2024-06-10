package lv.backend.models.users;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@ManyToMany(mappedBy = "myUsers", fetch = FetchType.EAGER)
	private Collection<Authorities> myAuthorities = new ArrayList<>();
	


	public User(@NotNull String name, @NotNull String username, @NotNull String password, @NotNull @Email String email,
			Collection<Reservation> reservations) {

		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.reservations = reservations;
	}

	//@Override
	//public String toString() {
		//return "User [idu=" + idu + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
			//	+ email + ", reservations=" + reservations + "]";
	//}

	public void addAuthority(Authorities authority) {
		if (!myAuthorities.contains(authority)) {
			myAuthorities.add(authority);
		}
	}

	public void removeAuthority(Authorities authority) {
		if (myAuthorities.contains(authority)) {
			myAuthorities.remove(authority);
		}
	}


}
	
	
	