package pl.witoldbrzezinski.transportmarket.load;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import pl.witoldbrzezinski.transportmarket.security.UserService;

public class LoadServiceImplTest {

  public static final long LOAD_ID = 1L;
  public static final String LOAD_NAME = "NAME";
  public static final String LOADING_CITY = "LOADING CITY";
  public static final String LOADING_POSTCODE = "00-000";
  public static final String UNLOADING_CITY = "UNLOADING CITY";
  public static final String UNLOADING_POSTCODE = "00-001";
  public static final String LOAD_TYPE = "TYPE";
  public static final int LOAD_WEIGHT = 10;
  public static final int LOAD_PRICE = 100;
  public static final String NEW_NAME = "NEW NAME";
  LoadService loadService;
  LoadMapper loadMapper;
  LoadRepository loadRepository;
  UserService userService;
  private Clock clock;

  @BeforeEach
  public void init() {
    loadRepository = mock(LoadRepository.class);
    loadMapper = new LoadMapper(new ModelMapper());
    userService = mock(UserService.class);
    clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    loadService = new LoadServiceImpl(loadRepository, loadMapper, userService);
  }

  @Test
  void shouldGetAllLoads() {
    // given
    LoadEntity load =
        new LoadEntity(
            LOAD_ID,
            LOAD_NAME,
            LocalDateTime.now(clock).plusDays(1),
            LOADING_CITY,
            LOADING_POSTCODE,
            LocalDateTime.now(clock).plusDays(2),
            UNLOADING_CITY,
            UNLOADING_POSTCODE,
            new BigDecimal(LOAD_WEIGHT),
            LOAD_TYPE,
            new BigDecimal(LOAD_PRICE));
    List<LoadEntity> loads = List.of(load);
    LoadDTOResponse loadDTOResponse =
        new LoadDTOResponse(
            LOAD_ID,
            LOAD_NAME,
            LocalDateTime.now(clock).plusDays(1),
            LOADING_CITY,
            LOADING_POSTCODE,
            LocalDateTime.now(clock).plusDays(2),
            UNLOADING_CITY,
            UNLOADING_POSTCODE,
            new BigDecimal(LOAD_WEIGHT),
            LOAD_TYPE,
            new BigDecimal(LOAD_PRICE));
    // when
    when(loadRepository.findAll()).thenReturn(loads);
    // then
    assertThat(loadDTOResponse).usingRecursiveComparison().isEqualTo(loadService.getAll().get(0));
  }

  @Test
  void shouldGetLoadById() {
    // given
    LoadEntity load =
        new LoadEntity(
            LOAD_ID,
            LOAD_NAME,
            LocalDateTime.now(clock).plusDays(1),
            LOADING_CITY,
            LOADING_POSTCODE,
            LocalDateTime.now(clock).plusDays(2),
            UNLOADING_CITY,
            UNLOADING_POSTCODE,
            new BigDecimal(LOAD_WEIGHT),
            LOAD_TYPE,
            new BigDecimal(LOAD_PRICE));
    // when
    when(loadRepository.findById(LOAD_ID)).thenReturn(Optional.of(load));
    // then
    assertThat(loadMapper.toDTO(load))
        .usingRecursiveComparison()
        .isEqualTo(loadService.getById(LOAD_ID));
  }

  @Test
  void shouldSaveLoad() {
    // given
    LoadDTORequest loadDTORequest =
        new LoadDTORequest(
            LOAD_ID,
            LOAD_NAME,
            LocalDateTime.now(clock).plusDays(1),
            LOADING_CITY,
            LOADING_POSTCODE,
            LocalDateTime.now(clock).plusDays(2),
            UNLOADING_CITY,
            UNLOADING_POSTCODE,
            new BigDecimal(LOAD_WEIGHT),
            LOAD_TYPE,
            new BigDecimal(LOAD_PRICE));
    LoadEntity load =
        new LoadEntity(
            LOAD_ID,
            LOAD_NAME,
            LocalDateTime.now(clock).plusDays(1),
            LOADING_CITY,
            LOADING_POSTCODE,
            LocalDateTime.now(clock).plusDays(2),
            UNLOADING_CITY,
            UNLOADING_POSTCODE,
            new BigDecimal(LOAD_WEIGHT),
            LOAD_TYPE,
            new BigDecimal(LOAD_PRICE));
    // when
    when(loadRepository.save(any(LoadEntity.class))).thenReturn(load);
    // then
    assertThat(loadService.save(loadDTORequest))
        .usingRecursiveComparison()
        .isEqualTo(loadMapper.toDTO(load));
  }

  @Test
  void shouldUpdateLoad(){
    // given
    LoadDTORequest loadDTORequest =
            new LoadDTORequest(
                    LOAD_ID,
                    LOAD_NAME,
                    LocalDateTime.now(clock).plusDays(1),
                    LOADING_CITY,
                    LOADING_POSTCODE,
                    LocalDateTime.now(clock).plusDays(2),
                    UNLOADING_CITY,
                    UNLOADING_POSTCODE,
                    new BigDecimal(LOAD_WEIGHT),
                    LOAD_TYPE,
                    new BigDecimal(LOAD_PRICE));
    LoadEntity load =
            new LoadEntity(
                    LOAD_ID,
                    LOAD_NAME,
                    LocalDateTime.now(clock).plusDays(1),
                    LOADING_CITY,
                    LOADING_POSTCODE,
                    LocalDateTime.now(clock).plusDays(2),
                    UNLOADING_CITY,
                    UNLOADING_POSTCODE,
                    new BigDecimal(LOAD_WEIGHT),
                    LOAD_TYPE,
                    new BigDecimal(LOAD_PRICE));
    //when
    load.setName(NEW_NAME);
    when(loadRepository.findById(LOAD_ID)).thenReturn(Optional.of(load));
    when(loadRepository.save(loadMapper.toEntity(loadDTORequest))).thenReturn(load);
    //then
    assertThat(loadService.update(LOAD_ID,loadDTORequest)).usingRecursiveComparison().isEqualTo(loadMapper.toDTO(load));
  }

  @Test
  void shouldDeleteBook(){
    //given
    LoadEntity load =
            new LoadEntity(
                    LOAD_ID,
                    LOAD_NAME,
                    LocalDateTime.now(clock).plusDays(1),
                    LOADING_CITY,
                    LOADING_POSTCODE,
                    LocalDateTime.now(clock).plusDays(2),
                    UNLOADING_CITY,
                    UNLOADING_POSTCODE,
                    new BigDecimal(LOAD_WEIGHT),
                    LOAD_TYPE,
                    new BigDecimal(LOAD_PRICE));
    load.setDeleted(false);
    //when
    when(loadRepository.findById(LOAD_ID)).thenReturn(Optional.of(load));
    loadService.delete(LOAD_ID);
    //then
    assertTrue(load.isDeleted());

  }
}
