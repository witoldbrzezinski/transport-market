package pl.witoldbrzezinski.transportmarket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.witoldbrzezinski.transportmarket.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	

}
