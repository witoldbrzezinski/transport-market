package pl.witoldbrzezinski.transportmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import pl.witoldbrzezinski.transportmarket.IntegrationTestDB;
import pl.witoldbrzezinski.transportmarket.service.LoadService;

@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles("test")
@Sql(value = "/clean-loads.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class LoadControllerTest extends IntegrationTestDB {
	
	@Autowired
	private LoadController loadController;
	
	@Autowired
	private LoadService loadService;
	@Autowired
	private MockMvc mockMvc;
	
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
