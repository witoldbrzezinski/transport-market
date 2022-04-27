package pl.witoldbrzezinski.transportmarket.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.witoldbrzezinski.transportmarket.entity.Load;
import pl.witoldbrzezinski.transportmarket.entity.User;


public class LoadServiceTest {
	
	
	LoadService loadService;
	
	private Load testLoad;
	
	@BeforeEach
	public void init() {
		loadService = mock(LoadService.class);
		LocalDateTime loadingDate = LocalDateTime.of(2100, Month.APRIL,1,10,00);
		LocalDateTime unloadingDate = LocalDateTime.of(2100, Month.APRIL,2,10,00);
		BigDecimal price = new BigDecimal(1000);
		BigDecimal weight = new BigDecimal(9);
		User user = new User();
		testLoad = new Load("Test", loadingDate, "Loading city", "00-000", unloadingDate, "Unloding city", "99-999", price, "TestLoad", weight, user);
	}
	
	
	@Test
	public void gettingTheListOfLoadsShouldReturnCorrectSize() {
		List<Load> loadList = loadService.getAllLoads();
		loadList.add(testLoad);
		assertThat(loadList.size()).isEqualTo(1);
	}

}
