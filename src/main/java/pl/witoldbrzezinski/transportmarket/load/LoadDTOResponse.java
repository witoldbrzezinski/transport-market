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
}
