package pl.witoldbrzezinski.transportmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import pl.witoldbrzezinski.transportmarket.service.LoadService;


@WebMvcTest(LoadController.class)
@AutoConfigureTestDatabase
public class LoadControllerTest {
	
	@Autowired
	private LoadController loadController;
	
	@MockBean
	private LoadService loadService;
	
	private MockMvc mockMvc;
	
	@Mock
	private Model model;
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(loadController).build();
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
	public void savingLoadWithNoLoadShouldReturnToAddingLoadPage() throws Exception {
		mockMvc.perform(post("/confirmLoadAdded"))
			.andExpect(status().isOk())
			.andExpect(view().name("add-load.html"));
	}
	

}
