package pl.witoldbrzezinski.transportmarket.security;

import java.util.HashSet;
import java.util.Set;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;
import pl.witoldbrzezinski.transportmarket.load.LoadEntity;
import pl.witoldbrzezinski.transportmarket.validator.FieldsValueMatch;

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
    private Long userId;

	@Column(unique=true)
	@NotNull(message = "Username can't be empty")
	@NotBlank(message = "Username can't be empty")
	private String username;

	@NotNull
	@NotBlank
	private String password;

	@Transient
	private String matchingPassword;

	private int enabled;

	@Column(unique = true)
	@NotNull
	@NotBlank
	private String email;

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			    name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> userRoles = new HashSet<>();

	@ManyToOne
	@JoinColumn(name="customer_id")
	private CustomerEntity customer;

	@OneToMany(mappedBy="user")
	private Set<LoadEntity> loads;

	public UserEntity(@NotNull @NotBlank String username, @NotNull @NotBlank String password, int enabled,
					  @NotNull @NotBlank String email, Set<RoleEntity> roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.userRoles = roles;
	}
    // TODO: delete this
	public void setRoles(Set<RoleEntity> roles) {
		this.userRoles = roles;
	}

}
