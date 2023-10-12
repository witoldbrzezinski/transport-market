package pl.witoldbrzezinski.transportmarket.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.witoldbrzezinski.transportmarket.security.UserEntity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

	private String name;

	private String country;

	private String city;

	private String email;

	private String phone;

	@OneToMany(mappedBy="customer")
	private Set<UserEntity> users;


	public CustomerEntity(String name, String country, String city, String email, String phone) {
		this.name = name;
		this.country = country;
		this.city = city;
		this.email = email;
		this.phone = phone;
	}

}
