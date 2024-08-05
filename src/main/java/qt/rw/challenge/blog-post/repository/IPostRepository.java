package rca.ac.year3.security_starter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rca.ac.year3.security_starter.models.Author;
import rca.ac.year3.security_starter.models.Post;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthor(Author author);
}
