package qt.rw.challenge.blog_post.services;
import qt.rw.challenge.blog_post.dto.CommentDTO;
import qt.rw.challenge.blog_post.models.Comment;
import qt.rw.challenge.blog_post.models.Post;

import java.util.List;
import java.util.UUID;

public interface ICommentService {
    List<Comment> findAll();
    Comment findById(UUID id);
    Comment create(CommentDTO commentCreateDTO);
    Comment update(UUID id, CommentDTO commentDTO);
    void delete(UUID id);

    List<Comment> findByAuthor(UUID id);
}
// This method is not needed
