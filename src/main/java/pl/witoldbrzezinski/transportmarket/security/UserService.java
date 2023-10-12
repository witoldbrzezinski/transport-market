package pl.witoldbrzezinski.transportmarket.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	
	private static final int ENABLED_ON = 1;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserEntity registerUser(String username, String password, String matchingPassword, String email)  {
			UserEntity user = new UserEntity();
			RoleEntity role = new RoleEntity();
			user.setEnabled(ENABLED_ON);	
			user.setUsername(username);
			if (password.equals(matchingPassword)) {
				user.setPassword(passwordEncoder.encode(password));
				user.setMatchingPassword(matchingPassword);
			} else{
				throw new RuntimeException("Passwords do not match!");
			}
			user.setEmail(email);
			role.setRole(RoleEnum.ROLE_USER);
			user.setRoles(Set.of(role));
			return userRepository.save(user);				
	}
	
	public boolean checkIfUsernameExist(String username) {
		return userRepository.findByUsername(username).isPresent();
	}
	
	public boolean checkIfEmailExist(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
	
	public UserEntity getUserByUsername(String username) {
		return userRepository.findByUsernameEquals(username).get(0);
		
	}
	
	public UserEntity getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		UserEntity user = getUserByUsername(currentUsername);
		return user;
		
	}
	
}

