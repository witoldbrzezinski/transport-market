package pl.witoldbrzezinski.transportmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "username")
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

	@Column(name = "email")
	@NotNull
	@NotEmpty
	private String email;

	public User() {

	}

	public User(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password, int enabled,
			@NotNull @NotEmpty String email) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}
