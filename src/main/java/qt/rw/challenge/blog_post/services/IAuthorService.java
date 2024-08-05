package qt.rw.challenge.blog_post.services;

import qt.rw.challenge.blog_post.dto.UserRegistrationDTO;
import qt.rw.challenge.blog_post.models.Author;

import java.util.List;
import java.util.UUID;

public interface IAuthorService {

     List<Author> findAll();

     Author findById(UUID id);

     Author save(UserRegistrationDTO dto);

     void validateNewRegistration(Author customer);

     boolean isNotUnique(Author customer);

     void deleteById(UUID id);
}
