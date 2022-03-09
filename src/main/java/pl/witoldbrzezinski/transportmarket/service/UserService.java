package pl.witoldbrzezinski.transportmarket.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.witoldbrzezinski.transportmarket.entity.Role;
import pl.witoldbrzezinski.transportmarket.entity.RoleEnum;
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
			Role role = new Role();
			user.setEnabled(ENABLED_ON);	
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);
			role.setRole(RoleEnum.ROLE_USER);
			user.setRoles(Set.of(role));
			System.out.println(user);
			System.out.println(role);
			return userRepository.save(user);				
	}
		
}
