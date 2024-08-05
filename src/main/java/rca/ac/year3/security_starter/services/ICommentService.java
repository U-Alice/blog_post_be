package rca.ac.year3.security_starter.services;
import rca.ac.year3.security_starter.dto.CommentDTO;
import rca.ac.year3.security_starter.models.Comment;
import rca.ac.year3.security_starter.models.Post;

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
