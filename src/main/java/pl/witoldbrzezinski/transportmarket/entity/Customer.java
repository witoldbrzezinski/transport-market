package pl.witoldbrzezinski.transportmarket.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
	
	@Column(name = "name")
	private String name;
	  
	@Column(name = "country")
	private String country;

	@Column(name="city")
	private String city;
	  
	@Column(name="email")
	private String email;
	  
	@Column(name="phone")
	private String phone;

	@OneToMany(mappedBy="customer")
	private Set<User> users;
	
	public Customer() {
		  
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", country=" + country + ", city=" + city
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	   
}
