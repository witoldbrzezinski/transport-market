package pl.witoldbrzezinski.transportmarket.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService {
	
	@Autowired
	CustomerRepository repository;
	
    public List<CustomerEntity> getAllCustomers()
    {
        List<CustomerEntity> customerList = repository.findAll();
         
        if(customerList.size() > 0) {
            return customerList;
        } else {
            return new ArrayList<CustomerEntity>();
        }
    }

}
