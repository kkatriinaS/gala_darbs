package lv.backend.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private String name;
	private String username;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(String name, String username, String password, String email,
			Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;

	}

	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

