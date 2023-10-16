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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

@Entity
@Table(name = "loads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
public class LoadEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDateTime loadingDate;

  private String loadingCity;

  private String loadingPostcode;

  private LocalDateTime unloadingDate;

  private String unloadingCity;

  private String unloadingPostcode;

  @Column(name = "weight_in_tones")
  private BigDecimal weight;

  private String loadType;

  @Column(name = "price_in_pln")
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
