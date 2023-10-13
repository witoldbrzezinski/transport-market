package pl.witoldbrzezinski.transportmarket.security;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role",unique = true)
	@NotNull
	private RoleEnum role;
	
	@ManyToMany(mappedBy = "userRoles", cascade=CascadeType.ALL)
	private Set<UserEntity> users;

	public RoleEntity(@NotNull RoleEnum role) {
		this.role = role;
	}

	public static RoleEntity createUserWithDefaultRole() {
		return new RoleEntity(RoleEnum.ROLE_USER);
	}
	
	
}

