package pl.witoldbrzezinski.transportmarket.security;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByEmail(String email);

  List<UserEntity> findByUsernameEquals(String name);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
