package pl.witoldbrzezinski.transportmarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.witoldbrzezinski.transportmarket.entity.Customer;
import pl.witoldbrzezinski.transportmarket.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository repository;
	
    public List<Customer> getAllCustomers()
    {
        List<Customer> customerList = repository.findAll();
         
        if(customerList.size() > 0) {
            return customerList;
        } else {
            return new ArrayList<Customer>();
        }
    }

}
