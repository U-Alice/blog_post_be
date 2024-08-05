package qt.rw.challenge.blog_post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qt.rw.challenge.blog_post.models.Author;
import qt.rw.challenge.blog_post.models.Post;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthor(Author author);
}
