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

  @Override
  public CustomerDTOResponse getById(Long id) {
    CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    return customerMapper.toDTO(customerEntity);
  }

  @Override
  public CustomerDTOResponse save(CustomerDTORequest customerDTORequest) {
    CustomerEntity customerEntity =customerMapper.toEntity(customerDTORequest);
    customerRepository.save(customerEntity);
    return customerMapper.toDTO(customerEntity);
  }

  @Override
  public CustomerDTOResponse update(Long id, CustomerDTORequest customerDTORequest) {
    CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    customerEntity.setName(customerDTORequest.getName());
    customerEntity.setCountry(customerDTORequest.getCountry());
    customerEntity.setCity(customerDTORequest.getCity());
    customerEntity.setEmail(customerDTORequest.getEmail());
    customerEntity.setPhone(customerDTORequest.getPhone());
    return customerMapper.toDTO(customerEntity);
  }

  @Override
  public void delete(Long id) {
    CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    customerEntity.setDeleted(true);

  }
}
