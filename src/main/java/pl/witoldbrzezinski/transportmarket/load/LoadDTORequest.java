package pl.witoldbrzezinski.transportmarket.load;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoadDTORequest {

  private Long id;

  @NotEmpty(message = "Load name cannot be empty")
  private String name;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @FutureOrPresent(message = "Loading date must be in the future or present")
  @NotNull(message = "Loading date cannot be empty")
  private LocalDateTime loadingDate;

  @NotEmpty(message = "Loading city cannot be empty")
  private String loadingCity;

  @NotEmpty(message = "Loading postcode cannot be empty")
  @Pattern(regexp = "\\d{2}-\\d{3}", message = "Wrong postcode format")
  private String loadingPostcode;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @Future(message = "Unloading date must be in the future")
  private LocalDateTime unloadingDate;

  @NotEmpty(message = "Unloading city cannot be empty")
  private String unloadingCity;

  @NotEmpty(message = "Unloading postcode cannot be empty")
  @Pattern(regexp = "\\d{2}-\\d{3}", message = "Wrong postcode format")
  private String unloadingPostcode;

  @NotNull(message = "Weight cannot be empty")
  @Min(value = 0, message = "Weight must be greater than 0")
  private BigDecimal weight;

  @NotEmpty(message = "Load type cannot be empty")
  private String loadType;

  @NotNull(message = "Price cannot be empty")
  @Min(value = 0, message = "Price must be greater than 0")
  private BigDecimal price;

  private UserEntity user;

  public LoadDTORequest(Long id, String name, LocalDateTime loadingDate, String loadingCity, String loadingPostcode, LocalDateTime unloadingDate, String unloadingCity, String unloadingPostcode, BigDecimal weight, String loadType, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.loadingDate = loadingDate;
    this.loadingCity = loadingCity;
    this.loadingPostcode = loadingPostcode;
    this.unloadingDate = unloadingDate;
    this.unloadingCity = unloadingCity;
    this.unloadingPostcode = unloadingPostcode;
    this.weight = weight;
    this.loadType = loadType;
    this.price = price;
  }
}
