package pl.witoldbrzezinski.transportmarket.load;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

@Entity
@Table(name = "loads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoadEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Load name cannot be empty")
  private String name;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @FutureOrPresent(message = "Loading date must be in the future or present")
  @NotNull(message = "Load date cannot be empty")
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

  @Column(name = "weight_in_tones")
  @Min(value = 0, message = "Weight must be greater than 0")
  private BigDecimal weight;

  @NotEmpty(message = "Load type cannot be empty")
  private String loadType;

  @Column(name = "price_in_pln")
  @NotNull(message = "Price cannot be empty")
  @Min(value = 0, message = "Price must be greater than 0")
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  private boolean isDeleted;

  private final String uuid = UUID.randomUUID().toString();

  @Version private Long version;

  public LoadEntity(
      String name,
      LocalDateTime loadingDate,
      String loadingCity,
      String loadingPostcode,
      LocalDateTime unloadingDate,
      String unloadingCity,
      String unloadingPostcode,
      BigDecimal weight,
      String loadType,
      BigDecimal price,
      UserEntity user) {
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
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LoadEntity that = (LoadEntity) o;
    return Objects.equals(uuid, that.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
