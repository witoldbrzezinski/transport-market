package pl.witoldbrzezinski.transportmarket.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;

public interface UserService {

    UserDTORegisterResponse registerNewUser(UserDTORegisterRequest userDTORegisterRequest);

    boolean checkIfUsernameExist(String username);

    boolean checkIfEmailExist(String email);

    UserEntity getUserByUsername(String username);

    UserEntity getCurrentUser();


}
