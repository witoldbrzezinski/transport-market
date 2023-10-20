package pl.witoldbrzezinski.transportmarket.customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import pl.witoldbrzezinski.transportmarket.IntegrationTestDB;

@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles("test")
@Sql(value = "/clean-customers.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CustomerIntegrationTest extends IntegrationTestDB {

  @Autowired private MockMvc mockMvc;

  @Test
  @SneakyThrows
  public void gettingAllCustomersShouldReturnCustomersList() {
    mockMvc
        .perform(get("/customers"))
        .andExpect(status().isOk())
        .andExpect(view().name("customer.html"));
  }
}
