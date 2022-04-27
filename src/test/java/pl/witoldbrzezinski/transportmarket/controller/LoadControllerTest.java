package pl.witoldbrzezinski.transportmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;


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

import pl.witoldbrzezinski.transportmarket.entity.Load;
import pl.witoldbrzezinski.transportmarket.entity.User;
import pl.witoldbrzezinski.transportmarket.service.LoadService;


@WebMvcTest(LoadController.class)
@AutoConfigureTestDatabase
public class LoadControllerTest {
	
	@Autowired
	private LoadController loadController;
	
	@MockBean
	private LoadService loadService;
	
	@Mock
	private Model model;
	
	private MockMvc mockMvc;
	
	private Load testLoad;
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(loadController).build();
		
		LocalDateTime loadingDate = LocalDateTime.of(2100, Month.APRIL,1,10,00);
		LocalDateTime unloadingDate = LocalDateTime.of(2100, Month.APRIL,2,10,00);
		BigDecimal price = new BigDecimal(1000);
		BigDecimal weight = new BigDecimal(9);
		User user = new User();
		testLoad = new Load("Test", loadingDate, "Loading city", "00-000", unloadingDate, "Unloding city", "99-999", price, "TestLoad", weight, user);
	}
	
	@Test
	public void controllerShouldNotBeNull() {
		assertThat(loadController).isNotNull();
	}
	
	@Test
	public void newLoadButtonShouldReturnView() throws Exception {
		mockMvc.perform(get("/addNewLoad"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-load.html"));
	}
	
	@Test
	public void savingLoad() throws Exception {
		mockMvc.perform(post("/confirmLoadAdded"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-load.html"));
	}
	

}
