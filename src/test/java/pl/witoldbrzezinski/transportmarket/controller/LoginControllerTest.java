package pl.witoldbrzezinski.transportmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import pl.witoldbrzezinski.transportmarket.service.UserService;

@WebMvcTest(LoginController.class)
@AutoConfigureTestDatabase
public class LoginControllerTest {
	
	@Autowired
	LoginController loginController;
	
	@MockBean
	UserService userService;
	
	private MockMvc mockMvc;
	
	@Mock
    private Model model;
	
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
		}
	
	@Test
	public void controllerShouldNotBeNull() {
		assertThat(loginController).isNotNull();
	}
	
	@Test
	public void gettingLoginPageShouldReturnLoginView() throws Exception {
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("login.html"));
	}
	
	@Test
	public void gettingRegisterPageShouldReturnRegisterView() throws Exception {
		mockMvc.perform(get("/register"))
		.andExpect(status().isOk())
		.andExpect(view().name("register.html"));
	}

}
