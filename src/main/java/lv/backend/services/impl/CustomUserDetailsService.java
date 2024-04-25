package lv.backend.services.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.backend.models.users.User;
import lv.backend.repos.IUserRepo;
import lv.backend.security.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private IUserRepo userRepo;

	public CustomUserDetailsService(IUserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username or Password not found");
		}
		return new CustomUserDetails(user.getName(), user.getUsername(), user.getPassword(), user.getEmail(),
				authorities());
	}

	public Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}
