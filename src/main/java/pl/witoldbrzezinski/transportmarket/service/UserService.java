package pl.witoldbrzezinski.transportmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.witoldbrzezinski.transportmarket.entity.User;
import pl.witoldbrzezinski.transportmarket.repository.UserRepository;

@Service("userService")
public class UserService {
	
	private static final int ENABLED_ON = 1;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User registerUser(String username, String password, String email) {	
			User user = new User();		
			user.setEnabled(ENABLED_ON);	
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			System.out.println(user);
			return userRepository.save(user);			
		
	}
		
}

