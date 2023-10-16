package pl.witoldbrzezinski.transportmarket.security;

public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException(String username) {
        super(String.format("User with name %s already exists", username));
    }

}
