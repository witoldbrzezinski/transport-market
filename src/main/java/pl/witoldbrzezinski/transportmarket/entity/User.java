package pl.witoldbrzezinski.transportmarket.entity;

import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
    private Long userId;
	
	@Column(name = "username", unique=true)
	@NotNull
	@NotEmpty
	private String username;

	@Column(name = "password")
	@NotNull
	@NotEmpty
	private String password;

	private String matchingPassword;

	@Column(name = "enabled")
	private int enabled;

	@Column(name = "email", unique = true)
	@NotNull
	@NotEmpty
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(	
			    name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> userRoles = new HashSet<>();

	public User() {
	}

	public User(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password, int enabled,
			@NotNull @NotEmpty String email, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.userRoles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Role> getRoles() {
		return userRoles;
	}

	public void setRoles(Set<Role> roles) {
		this.userRoles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enabled, matchingPassword, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && enabled == other.enabled
				&& Objects.equals(matchingPassword, other.matchingPassword) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", enabled=" + enabled + ", email=" + email + "]";
	}


}
