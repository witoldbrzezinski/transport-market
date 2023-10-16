package pl.witoldbrzezinski.transportmarket.security;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.witoldbrzezinski.transportmarket.customer.CustomerRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private static final int ENABLED_ON = 1;
  private static final Integer ROLE_USER_ID = 2;

  private final UserRepository userRepository;
  private final CustomerRepository customerRepository;
  private final RoleRepository roleRepository;
  @Autowired PasswordEncoder passwordEncoder;

  public UserEntity registerUser(
      String username, String password, String matchingPassword, String email) {
    UserEntity user = new UserEntity();
    RoleEntity role = roleRepository.getById(ROLE_USER_ID);
    user.setEnabled(ENABLED_ON);
    // TODO change this line
    user.setCustomer(customerRepository.getById(1L));
    user.setUsername(username);
    if (password.equals(matchingPassword)) {
      user.setPassword(passwordEncoder.encode(password));
      user.setMatchingPassword(matchingPassword);
    } else {
      throw new RuntimeException("Passwords do not match!");
    }
    user.setEmail(email);
    //	role.setRole(RoleEnum.ROLE_USER);
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
    return getUserByUsername(currentUsername);
  }
}
