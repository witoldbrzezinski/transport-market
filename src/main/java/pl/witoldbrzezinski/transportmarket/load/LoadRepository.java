package pl.witoldbrzezinski.transportmarket.load;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.witoldbrzezinski.transportmarket.load.LoadEntity;

@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {
	
	Page<LoadEntity> findAll(Pageable pageable);
	LoadEntity findByName(String name);

}
