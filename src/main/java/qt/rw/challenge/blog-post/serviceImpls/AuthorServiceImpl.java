package rca.ac.year3.security_starter.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rca.ac.year3.security_starter.dto.UserRegistrationDTO;
import rca.ac.year3.security_starter.enums.EUserStatus;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.exceptions.BadRequestException;
import rca.ac.year3.security_starter.exceptions.ResourceNotFoundException;
import rca.ac.year3.security_starter.models.Author;
import rca.ac.year3.security_starter.models.Role;
import rca.ac.year3.security_starter.repository.IAuthorRepository;
import rca.ac.year3.security_starter.services.IAuthorService;
import rca.ac.year3.security_starter.services.IRoleService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements IAuthorService {

    private final IRoleService roleService;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public AuthorServiceImpl(IRoleService roleService) {
        this.roleService = roleService;
    }

    public List<Author> findAll() {
        try {
            return authorRepository.findAll();
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    public Author findById(UUID id) {
        try {
            return authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("customer", "id", id));
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    public Author save(UserRegistrationDTO dto) {
//        try{
            Author author = new Author();
            String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
            Role role = roleService.findByName(Erole.AUTHOR);
            author.setEmail(dto.getEmail());
            author.setFirstName(dto.getFirstName());
            author.setLastName(dto.getLastName());
            author.setPassword(encodedPassword);
            author.setStatus(EUserStatus.ACTIVE);
            author.setRoles(Collections.singleton(role));
            validateNewRegistration(author);
            return authorRepository.save(author);
//        }catch (Exception e){
//            throw e;
////            ExceptionUtils.handleServiceExceptions(e);
////            return null;
//        }
    }
    //TODO: validate DOB
    @Override
    public void validateNewRegistration(Author customer) {
        if (isNotUnique(customer)) {
            throw new BadRequestException(String.format("User with customer '%s' already exists", customer.getEmail()));
        }
    }

    @Override
    public boolean isNotUnique(Author customer) {
        Optional<Author> userOptional = this.authorRepository.findByEmail(customer.getEmail());
        return userOptional.isPresent();
    }

    public void deleteById(UUID id) {
        try {
            authorRepository.deleteById(id);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
        }
    }
}
