package rca.ac.year3.security_starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.ac.year3.security_starter.models.Author;
import rca.ac.year3.security_starter.models.Comment;
import rca.ac.year3.security_starter.models.Post;

import java.util.List;
import java.util.UUID;

public interface ICommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByAuthor(Author author);

    List<Comment> findByPost(Post post);
}
