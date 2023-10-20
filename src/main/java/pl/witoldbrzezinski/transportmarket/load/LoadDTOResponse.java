package pl.witoldbrzezinski.transportmarket.load;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class LoadDTOResponse {

  private Long id;
  private String name;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime loadingDate;

  private String loadingCity;
  private String loadingPostcode;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime unloadingDate;

  private String unloadingCity;
  private String unloadingPostcode;
  private BigDecimal weight;
  private String loadType;
  private BigDecimal price;
  private UserEntity user;

  public LoadDTOResponse(
      Long id,
      String name,
      LocalDateTime loadingDate,
      String loadingCity,
      String loadingPostcode,
      LocalDateTime unloadingDate,
      String unloadingCity,
      String unloadingPostcode,
      BigDecimal weight,
      String loadType,
      BigDecimal price) {
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
