package pl.witoldbrzezinski.transportmarket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.witoldbrzezinski.transportmarket.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	
	CustomerEntity findByName(String name);
}
