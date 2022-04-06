package pl.witoldbrzezinski.transportmarket.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.witoldbrzezinski.transportmarket.entity.Load;

@Repository
public interface LoadRepository extends JpaRepository<Load, Long> {
	
	Page<Load> findAll(Pageable pageable);

}
