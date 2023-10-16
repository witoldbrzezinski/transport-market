package pl.witoldbrzezinski.transportmarket.load;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class LoadTest {

	@Autowired
	private LoadRepository loadRepository;
	
	private LoadEntity testLoad;
	
	@BeforeEach
	public void init() {
		LocalDateTime loadingDate = LocalDateTime.of(2100, Month.APRIL,1,10,00);
		LocalDateTime unloadingDate = LocalDateTime.of(2100, Month.APRIL,2,10,00);
		BigDecimal price = new BigDecimal(1000);
		BigDecimal weight = new BigDecimal(9);
		UserEntity user = new UserEntity();
		testLoad = new LoadEntity("Test", loadingDate, "Loading city", "00-000", unloadingDate, "Unloding city", "99-999", price, "TestLoad", weight, user);
	}
	
	
	@Test
	public void createNewLoadShouldNotBeNull() {
		LoadEntity savedLoad = loadRepository.save(testLoad);
		assertNotNull(savedLoad);
	}
	
	@Test
	public void searchingLoadByNameShouldFindLoad() {
		String name = "Test";
		assertThat(testLoad.getName()).isEqualTo(name);
	
	}
	
	@Test
	public void searchingLoadWithWrongNameShouldNotFindLoad() {
		String name = "Wrong test";
		LoadEntity load = loadRepository.findByName(name);
		assertNull(load);
	
	}
	
	@Test
	public void listOfLoadsShouldHasSizeGreaterThanZero() {
		List<LoadEntity> loads = new ArrayList<>();
		loads.add(testLoad);
		assertThat(loads).size().isGreaterThan(0);
	}

	
	
}
