package pl.witoldbrzezinski.transportmarket.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.witoldbrzezinski.transportmarket.customer.CustomerEntity;
import pl.witoldbrzezinski.transportmarket.customer.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	private CustomerEntity testCustomer;
	
	@BeforeEach
	public void init() {
		testCustomer = new CustomerEntity("Customer","Poland","Warsaw","test@test.com","123456");
		
	}
	
	
	@Test
	public void createNewCustomerShouldNotBeNull() {
		CustomerEntity savedCustomer = customerRepository.save(testCustomer);
		assertNotNull(savedCustomer);
	}
	
	@Test
	public void searchingCustomerByNameShouldFindCustomer() {
		String name = "Customer";
		assertThat(testCustomer.getName()).isEqualTo(name);
	
	}
	
	@Test
	public void searchingCustomerWithWrongNameShouldNotFindCustomer() {
		String name = "Wrong name";
		CustomerEntity customer = customerRepository.findByName(name);
		assertNull(customer);
	
	}
	
	@Test
	public void listOfCustomersShouldHasSizeGreaterThanZero() {
		 List<CustomerEntity> customers = new ArrayList<>();
		 customers.add(testCustomer);
		 assertThat(customers).size().isGreaterThan(0); 
	}

	
	
}
