package rca.ac.year3.security_starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rca.ac.year3.security_starter.models.Author;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByEmail(String email);
}
