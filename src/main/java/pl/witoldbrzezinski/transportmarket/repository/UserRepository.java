package pl.witoldbrzezinski.transportmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.witoldbrzezinski.transportmarket.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsernameAndPassword(String username, String password);
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByEmail(String email);
	List<UserEntity> findByUsernameEquals(String name);
}
