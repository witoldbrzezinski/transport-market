package pl.witoldbrzezinski.transportmarket.customer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class CustomerServiceImplTest {

  public static final long ID = 1L;
  private static final String CUSTOMER_NAME = "CUSTOMER";
  public static final String CUSTOMER_COUNTRY = "POLAND";
  public static final String CUSTOMER_CITY = "WARSAW";
  public static final String CUSTOMER_EMAIL = "customer@customer.com";
  public static final String CUSTOMER_PHONE = "+48123456789";
  private CustomerRepository customerRepository;
  private CustomerMapper customerMapper;
  private CustomerService customerService;

  @BeforeEach
  public void init() {
    customerRepository = mock(CustomerRepository.class);
    customerMapper = new CustomerMapper(new ModelMapper());
    customerService = new CustomerServiceImpl(customerRepository, customerMapper);
  }

  @Test
  void shouldGetListOfAllCustomers() {
    // given
    CustomerEntity customerEntity =
        new CustomerEntity(
            ID, CUSTOMER_NAME, CUSTOMER_COUNTRY, CUSTOMER_CITY, CUSTOMER_EMAIL, CUSTOMER_PHONE);
    List<CustomerEntity> customers = List.of(customerEntity);
    CustomerDTOResponse customerDTOResponse =
        new CustomerDTOResponse(
            ID, CUSTOMER_NAME, CUSTOMER_COUNTRY, CUSTOMER_CITY, CUSTOMER_EMAIL, CUSTOMER_PHONE);
    // when
    when(customerRepository.findAll()).thenReturn(customers);
    // then
    assertThat(customerDTOResponse)
        .usingRecursiveComparison()
        .isEqualTo(customerService.getAll().get(0));
  }
}
