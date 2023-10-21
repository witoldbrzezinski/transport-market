package pl.witoldbrzezinski.transportmarket.load;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {

  @NotNull
  Page<LoadEntity> findAll(@NotNull Pageable pageable);

}
