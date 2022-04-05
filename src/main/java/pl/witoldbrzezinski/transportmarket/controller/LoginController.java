package pl.witoldbrzezinski.transportmarket.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.witoldbrzezinski.transportmarket.entity.User;
import pl.witoldbrzezinski.transportmarket.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String getloginPage(){	
		return "login.html";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("user",new User());
		return "register.html";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) throws Exception {
		if(userService.checkIfEmailExist(user.getEmail()) && userService.checkIfUsernameExist(user.getUsername())) {
			return "registration-existing-user.html";
		}
		try {
		User registeredUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getMatchingPassword(), user.getEmail());
		if (registeredUser!= null && !user.getUsername().isBlank() && !user.getPassword().isBlank() && !user.getMatchingPassword().isBlank() && ! user.getEmail().isBlank()) {
			 return "redirect:/";
		}
		}
		catch (Exception exc) {
			exc.printStackTrace();
			return "registration-error.html";
		}
		return "register.html";
	}
	
	@GetMapping("/myProfile")
	public String getMyProfilePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		User user = userService.getUserByUsername(currentUsername);
		model.addAttribute("user",user);
		return "profile.html";
	}
	
}
