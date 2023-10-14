package pl.witoldbrzezinski.transportmarket.load;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoadDTORequest {


  private String name;
  private LocalDateTime loadingDate;
  private String loadingCity;
  private String loadingPostcode;
  private LocalDateTime unloadingDate;
  private String unloadingCity;
  private String unloadingPostcode;
  private BigDecimal weight;
  private String loadType;
  private BigDecimal price;

}
