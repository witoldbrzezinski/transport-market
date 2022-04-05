package pl.witoldbrzezinski.transportmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.witoldbrzezinski.transportmarket.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsernameAndPassword(String username, String password);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	List<User> findByUsernameEquals(String name);
}
