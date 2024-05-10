package lv.backend.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lv.backend.dto.UserDto;
import lv.backend.models.users.User;
import lv.backend.repos.IUserRepo;
import lv.backend.services.IUserServices;
import lv.backend.utils.MyException;

@Service
public class UserServicesImplementation implements IUserServices {

	@Autowired
	private IUserRepo userRepo;

	public User createNewUser(String name, String username, String password, String email) {
		return userRepo.save(new User(0, name, username, password, email, null));
	}

	public User retrieveUserById(Long id) throws MyException {
		if (userRepo.existsById(id)) {
			return userRepo.findById(id).get();
		} else {
			throw new MyException("Wrong id");
		}
	}

	public ArrayList<User> retrieveAllUsers() {
		return (ArrayList<User>) userRepo.findAll();
	}

	public User updateUserById(Long id, String name, String username, String email) throws MyException {
		if (userRepo.existsById(id)) {
			User updatedUser = userRepo.findById(id).get();
			updatedUser.setName(name);
			updatedUser.setUsername(username);
			updatedUser.setEmail(email);
			return userRepo.save(updatedUser);

		} else {
			throw new MyException("Wrong id");
		}
	}

	public void deleteUserById(Long id) throws MyException {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
		} else {
			throw new MyException("Wrong id");
		}

	}

	@Override
	public User createNewUser(String name, String username, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(UserDto userDto) {
		User user;
		user = new User(0, userDto.getName(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), null);
		return userRepo.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
