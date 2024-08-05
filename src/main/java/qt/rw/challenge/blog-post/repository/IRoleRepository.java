package rca.ac.year3.security_starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.models.Role;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(Erole role);
}