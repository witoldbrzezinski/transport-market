package pl.witoldbrzezinski.transportmarket.controller;

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

import pl.witoldbrzezinski.transportmarket.service.CustomerService;

@WebMvcTest(CustomerController.class)
@AutoConfigureTestDatabase
public class CustomerControllerTest {
	
	@Autowired
	CustomerController customerController;
	
	@MockBean
	CustomerService customerService;
	
	private MockMvc mockMvc;
	
	@Mock
    private Model model;
	
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		}
	
	@Test
	public void gettingAllCustomersShouldReturnCustomersList() throws Exception {
		mockMvc.perform(get("/customers"))
		.andExpect(status().isOk())
		.andExpect(view().name("customer.html"));
	}
}
