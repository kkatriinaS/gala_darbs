package lv.backend.services;

import java.util.ArrayList;

import lv.backend.models.users.User;
import lv.backend.utils.MyException;

public interface IUserServices {

	User createNewUser(String name, String username, String email);

	User retrieveUserById(Long id) throws MyException;

	ArrayList<User> retrieveAllUsers();

	User updateUserById(Long id, String name, String username, String email) throws MyException;

	void deleteUserById(Long id) throws MyException;

}
