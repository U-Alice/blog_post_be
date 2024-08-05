package rca.ac.year3.security_starter.services;

import rca.ac.year3.security_starter.dto.UserRegistrationDTO;
import rca.ac.year3.security_starter.models.Author;

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
