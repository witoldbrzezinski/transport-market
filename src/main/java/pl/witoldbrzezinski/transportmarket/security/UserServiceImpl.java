package pl.witoldbrzezinski.transportmarket.security;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.witoldbrzezinski.transportmarket.customer.CustomerRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final Integer ENABLED_ON = 1;
  private static final Long STARTING_CUSTOMER_ID = 1L;
  private static final Integer ROLE_USER_ID = 2;

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final CustomerRepository customerRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDTORegisterResponse registerNewUser(UserDTORegisterRequest userDTORegisterRequest) {
    if (userRepository.existsByUsername(userDTORegisterRequest.getUsername())) {
      throw new UsernameAlreadyExistException(userDTORegisterRequest.getUsername());
    }
    if (userRepository.existsByEmail(userDTORegisterRequest.getEmail())) {
      throw new UserEmailAlreadyTakenException(userDTORegisterRequest.getEmail());
    }
    if (!userDTORegisterRequest
        .getPassword()
        .equals(userDTORegisterRequest.getMatchingPassword())) {
      throw new PasswordsNotMatchException();
    }
    UserEntity user =
        new UserEntity(userDTORegisterRequest.getUsername(), userDTORegisterRequest.getEmail());
    RoleEntity role = roleRepository.getReferenceById(ROLE_USER_ID);
    user.setEnabled(ENABLED_ON);
    Set<RoleEntity> roles = new HashSet<>();
    roles.add(role);
    user.setRoles(roles);
    user.setCustomer(customerRepository.getReferenceById(STARTING_CUSTOMER_ID));
    user.setPassword(passwordEncoder.encode(userDTORegisterRequest.getPassword()));
    user.setMatchingPassword(userDTORegisterRequest.getMatchingPassword());
    userRepository.save(user);
    return userMapper.registerRequestToDTO(user);
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
