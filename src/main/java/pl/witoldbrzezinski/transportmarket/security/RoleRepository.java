package pl.witoldbrzezinski.transportmarket.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

    Optional<RoleEntity> findByRole(RoleEnum role);

}
