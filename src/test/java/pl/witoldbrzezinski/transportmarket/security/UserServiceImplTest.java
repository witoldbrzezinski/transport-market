package pl.witoldbrzezinski.transportmarket.security;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.witoldbrzezinski.transportmarket.customer.CustomerRepository;

import java.util.Optional;
import java.util.Set;

public class UserServiceImplTest {

    private static final String USERNAME = "username";
    private static final String EMAIL = "username@transportmarket.com";
    private static final String PASSWORD = "password";

    private UserRepository userRepository;
    private UserMapper userMapper;
    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    public void init() {
        userRepository = mock(UserRepository.class);
        userMapper = new UserMapper(new ModelMapper());
        customerRepository = mock(CustomerRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository,userMapper,customerRepository,roleRepository,passwordEncoder);
    }

    @Test
    void shouldRegisterNewUser() {
        // given
        RoleEntity role = new RoleEntity(1,Role.ROLE_USER);
        UserEntity user = new UserEntity(1L, USERNAME, EMAIL, PASSWORD,PASSWORD);
        UserDTORegisterRequest userDTORegisterRequest =
                new UserDTORegisterRequest(USERNAME, EMAIL, PASSWORD,PASSWORD);
        // when
        when(roleRepository.findByRole(Role.ROLE_USER)).thenReturn(Optional.of(role));
        user.addRole(role);
        when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        // then
        assertThat(userService.registerNewUser(userDTORegisterRequest))
                .usingRecursiveComparison()
                .isEqualTo(userMapper.registerRequestToDTO(user));
    }


}
