package civa.user.repository;

import civa.user.model.entity.Role;
import civa.user.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de roles
 * @author Jamutaq Ortega
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    boolean existsByName(ERole name);
}
