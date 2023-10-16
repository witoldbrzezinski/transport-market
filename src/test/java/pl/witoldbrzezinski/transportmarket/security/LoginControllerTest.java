package pl.witoldbrzezinski.transportmarket.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.witoldbrzezinski.transportmarket.IntegrationTestDB;
import pl.witoldbrzezinski.transportmarket.security.LoginController;
import pl.witoldbrzezinski.transportmarket.security.UserService;

@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles("test")
// @Sql(value = "/clean-loads.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class LoginControllerTest extends IntegrationTestDB {

  @Autowired LoginController loginController;

  @Autowired UserService userService;
  @Autowired private MockMvc mockMvc;

  @Test
  public void controllerShouldNotBeNull() {
    assertThat(loginController).isNotNull();
  }

  @Test
  public void gettingLoginPageShouldReturnLoginView() throws Exception {
    mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login.html"));
  }

  @Test
  public void gettingRegisterPageShouldReturnRegisterView() throws Exception {
    mockMvc
        .perform(get("/register"))
        .andExpect(status().isOk())
        .andExpect(view().name("register.html"));
  }
}
