package lv.backend.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.backend.dto.UserDto;
import lv.backend.models.users.User;
import lv.backend.services.IUserServices;
import lv.backend.services.impl.UserServicesImplementation;

public class UserController {

	@Autowired
	private IUserServices userServices;
	private UserDetailsService userDetailsService;

	private UserServicesImplementation userServicesImplementation;

	public UserController(UserServicesImplementation userService) {
		this.userServicesImplementation = userService;
	}

	@GetMapping("/user/create")
	public String createUserGetFunc(User user, Model model) {
		model.addAttribute("allusers", userServices.retrieveAllUsers());
		return "user-create-page";
	}

	@PostMapping("/user/create")
	public String createUserPostFunc(@Validated User user, BindingResult result) {
		if (!result.hasErrors()) {
			userServices.createNewUser(user.getName(), user.getUsername(), user.getEmail());
			return "redirect:/user/showAll";
		} else {
			return "user-create-page";
		}
	}

	@GetMapping("/user/update/{id}")
	public String updateUserByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("user", userServices.retrieveUserById(id));
			model.addAttribute("allUsers", userServices.retrieveAllUsers());
			return "user-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/user/update/{id}")
	public String updateUserByIdPostFunc(@PathVariable("id") Long id, @Validated User user, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				User temp = userServices.updateUserById(id, user.getName(), user.getUsername(), user.getEmail());
				return "redirect:/user/showAll/" + temp.getIdu();
			} catch (Exception e) {
				return "redirect:/user/error";
			}
		} else {
			return "user-update-page";
		}
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUserById(@PathVariable("id") Long id, Model model) {
		try {
			userServices.deleteUserById(id);
			model.addAttribute("allusers", userServices.retrieveAllUsers());
			return "user-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/user/showAll/{id}")
	public String userByIdFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("user", userServices.retrieveUserById(id));
			return "user-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/user/showAll")
	public String allusersGetFunc(Model model) {
		model.addAttribute("allusers", userServices.retrieveAllUsers());
		return "user-all-page";
	}

	@GetMapping("/user/error")
	public String erroruserFunc() {
		return "error-page";
	}

	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userDetails);
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, UserDto userDto) {

		model.addAttribute("user", userDto);
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "register";
	}

	@PostMapping("/register")
	public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
		User user = userServicesImplementation.findByUsername(userDto.getUsername());
		if (user != null) {
			model.addAttribute("Userexist", user);
			return "register";
		}
		userServicesImplementation.save(userDto);
		return "redirect:/register?success";
	}

}
