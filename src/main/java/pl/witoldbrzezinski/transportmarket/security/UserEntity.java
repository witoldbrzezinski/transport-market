package pl.witoldbrzezinski.transportmarket.security;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;
import pl.witoldbrzezinski.transportmarket.load.LoadEntity;
import pl.witoldbrzezinski.transportmarket.utils.FieldsValueMatch;

@FieldsValueMatch(message = "Passwords do not match!")
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  @NotNull(message = "Username can't be empty")
  @NotBlank(message = "Username can't be empty")
  private String username;

  @NotNull @NotBlank private String password;

  @Transient private String matchingPassword;

  private int enabled;

  @Column(unique = true)
  @NotNull
  @NotBlank
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;

  @OneToMany(mappedBy = "user")
  private Set<LoadEntity> loads;

  private boolean isDeleted;

  private final String uuid = UUID.randomUUID().toString();

  @Version private Long version;

  public UserEntity(
      @NotNull @NotBlank String username,
      @NotNull @NotBlank String password,
      int enabled,
      @NotNull @NotBlank String email,
      Set<RoleEntity> roles) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.email = email;
    this.roles = roles;
  }

  // TODO: delete this
  public void setRoles(Set<RoleEntity> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity that = (UserEntity) o;
    return Objects.equals(uuid, that.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }
}
