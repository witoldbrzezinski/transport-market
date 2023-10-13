package pl.witoldbrzezinski.transportmarket.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

  CustomerEntity findByName(String name);
}
