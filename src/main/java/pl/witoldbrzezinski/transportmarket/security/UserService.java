package pl.witoldbrzezinski.transportmarket.security;


public interface UserService {

    UserDTORegisterResponse registerNewUser(UserDTORegisterRequest userDTORegisterRequest);

    boolean checkIfUsernameExist(String username);

    boolean checkIfEmailExist(String email);

    UserEntity getUserByUsername(String username);

    UserEntity getCurrentUser();


}
