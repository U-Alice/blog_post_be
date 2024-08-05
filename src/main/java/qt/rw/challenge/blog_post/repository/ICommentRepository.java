package qt.rw.challenge.blog_post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qt.rw.challenge.blog_post.models.Author;
import qt.rw.challenge.blog_post.models.Comment;
import qt.rw.challenge.blog_post.models.Post;

import java.util.List;
import java.util.UUID;

public interface ICommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByAuthor(Author author);

    List<Comment> findByPost(Post post);
}
