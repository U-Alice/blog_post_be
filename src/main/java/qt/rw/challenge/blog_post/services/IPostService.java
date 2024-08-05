package qt.rw.challenge.blog_post.services;

import qt.rw.challenge.blog_post.dto.CommentDTO;
import qt.rw.challenge.blog_post.dto.PostDTO;
import qt.rw.challenge.blog_post.models.Author;
import qt.rw.challenge.blog_post.models.Comment;
import qt.rw.challenge.blog_post.models.Post;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    Post create(PostDTO postCreateDTO);
    Post update(UUID id, PostDTO postDTO);
    void delete(UUID id);
    List<Post> findAll();

    Post findById(UUID id);

    List<Post> findByAuthor(UUID id);

    Comment addCommentToPost(UUID postId, String comment);


    List<Comment> getCommentsByPost(UUID postId);
}
