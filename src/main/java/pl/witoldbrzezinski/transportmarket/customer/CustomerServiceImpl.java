package pl.witoldbrzezinski.transportmarket.customer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public List<CustomerDTOResponse> getAll() {
    return customerRepository.findAll().stream().map(customerMapper::toDTO).toList();
  }

}
