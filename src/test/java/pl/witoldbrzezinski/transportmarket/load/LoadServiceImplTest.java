package pl.witoldbrzezinski.transportmarket.load;

import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

public class LoadServiceImplTest {

  LoadServiceImpl loadService;

  private LoadEntity testLoad;

  @BeforeEach
  public void init() {
    loadService = mock(LoadServiceImpl.class);
    LocalDateTime loadingDate = LocalDateTime.of(2100, Month.APRIL, 1, 10, 00);
    LocalDateTime unloadingDate = LocalDateTime.of(2100, Month.APRIL, 2, 10, 00);
    BigDecimal price = new BigDecimal(1000);
    BigDecimal weight = new BigDecimal(9);
    UserEntity user = new UserEntity();
    testLoad =
        new LoadEntity(
            "Test",
            loadingDate,
            "Loading city",
            "00-000",
            unloadingDate,
            "Unloding city",
            "99-999",
            price,
            "TestLoad",
            weight,
            user);
  }

  @Test
  public void gettingTheListOfLoadsShouldReturnCorrectSize() {
    List<LoadDTOResponse> loadList = loadService.getAll();
    //	loadList.add(testLoad);
    //	assertThat(loadList.size()).isEqualTo(1);
  }
}
