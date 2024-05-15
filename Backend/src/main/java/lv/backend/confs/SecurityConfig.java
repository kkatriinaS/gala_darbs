package lv.backend.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.servlet.DispatcherType;
import lv.backend.services.impl.CustomUserDetailsServiceImpl;


@Configuration
@EnableWebMvc
public class SecurityConfig {


	@Bean
	public CustomUserDetailsServiceImpl userDetailsManager() {
		return new CustomUserDetailsServiceImpl();
	}
	@Bean	
	PasswordEncoder passwordEncoderSimple2() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(userDetailsManager()).passwordEncoder(passwordEncoderSimple2());
		return authenticationManagerBuilder.build();
	}
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/parkingArea/showAll").permitAll()
		.requestMatchers("/parkingArea/delete").hasAnyAuthority("ADMIN")
		.requestMatchers("/parkingArea/create").permitAll()
		.requestMatchers("/parkingSpot/create").permitAll()
		.requestMatchers("/parkingSpot/showAll").permitAll()
		.requestMatchers("/parkingArea/update/**").permitAll()
		.requestMatchers("/home").permitAll()
		.requestMatchers("/register").permitAll()
		.requestMatchers("/user/error").permitAll()
	   
		
		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll().and().formLogin()
		.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/my-access-denied").and().csrf().disable();
     
		return http.build();

	}

	
}