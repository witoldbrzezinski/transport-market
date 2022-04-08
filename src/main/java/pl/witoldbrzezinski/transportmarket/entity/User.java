package pl.witoldbrzezinski.transportmarket.entity;

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

import pl.witoldbrzezinski.transportmarket.validator.FieldsValueMatch;

@FieldsValueMatch(message = "Passwords do not match!")
@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
    private Long userId;
	
	@Column(name = "username", unique=true)
	@NotNull(message = "Username can't be empty")
	@NotBlank(message = "Username can't be empty")
	private String username;

	@Column(name = "password")
	@NotNull
	@NotBlank
	private String password;
	
	@Transient
	private String matchingPassword;

	@Column(name = "enabled")
	private int enabled;

	@Column(name = "email", unique = true)
	@NotNull
	@NotBlank
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(	
			    name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> userRoles = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy="user")
	private Set<Load> loads;
	
	public User() {
	}

	public User(@NotNull @NotBlank String username, @NotNull @NotBlank String password, int enabled,
			@NotNull @NotBlank String email, Set<Role> roles) {
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
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Load> getLoads() {
		return loads;
	}

	public void setLoads(Set<Load> loads) {
		this.loads = loads;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", enabled=" + enabled + ", email=" + email
				+ ", userRoles=" + userRoles + "]";
	}

	
}
