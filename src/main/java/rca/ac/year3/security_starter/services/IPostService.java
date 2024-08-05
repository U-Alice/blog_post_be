package rca.ac.year3.security_starter.services;

import rca.ac.year3.security_starter.dto.PostDTO;
import rca.ac.year3.security_starter.models.Author;
import rca.ac.year3.security_starter.models.Post;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    Post create(PostDTO postCreateDTO);
    Post update(UUID id, PostDTO postDTO);
    void delete(UUID id);
    List<Post> findAll();

    Post findById(UUID id);

    List<Post> findByAuthor(UUID id);
}
