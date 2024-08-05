package qt.rw.challenge.blog_post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.rw.challenge.blog_post.models.Author;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByEmail(String email);
}
