package rca.ac.year3.security_starter.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rca.ac.year3.security_starter.dto.AdminRegistrationDTO;
import rca.ac.year3.security_starter.dto.UserRegistrationDTO;
import rca.ac.year3.security_starter.enums.EUserStatus;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.exceptions.BadRequestException;
import rca.ac.year3.security_starter.exceptions.ResourceNotFoundException;
import rca.ac.year3.security_starter.models.Role;
import rca.ac.year3.security_starter.models.UserData;
import rca.ac.year3.security_starter.repository.UserDataRepository;
import rca.ac.year3.security_starter.services.IRoleService;
import rca.ac.year3.security_starter.services.IUserService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;
import rca.ac.year3.security_starter.utils.Profile;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    private final IRoleService roleService;
    private final UserDataRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;


    @Value("yooo")
    private String adminRegistrationKey;


    @Autowired
    public UserServiceImpl(IRoleService iRoleService, UserDataRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.roleService = iRoleService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserData findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));
    }


    @Override
    public UserData getLoggedInUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser")
            throw new BadRequestException("You are not logged in, try to log in");

        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", email));
    }

    @Override
    public Profile getLoggedInProfile() {
        UserData theUser = getLoggedInUser();
        Object profile = null;
        Optional<Role> role = theUser.getRoles().stream().findFirst();
        if (role.isPresent()) {
            profile = theUser;
            return new Profile(profile);
        }
        return null;
    }

    @Override
    public UserData getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email));
    }


    @Override
    public UserData save(UserData user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserData registerAdmin(AdminRegistrationDTO dto) {
        try {
            if(!dto.getKey().equals(adminRegistrationKey)){
                throw new BadRequestException("Provided admin key is not valid!");
            }

            UserData user = new UserData();

            String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
            Role role = roleService.findByName(Erole.ADMIN);
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setPassword(encodedPassword);
            user.setStatus(EUserStatus.ACTIVE);
            user.setRoles(Collections.singleton(role));

            validateNewRegistration(user);

            return this.userRepository.save(user);
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
    @Override
    public UserData registerUser(UserRegistrationDTO dto) {
        try {

            UserData user = new UserData();

            String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
            Role role = roleService.findByName(Erole.AUTHOR);
            user.setEmail(dto.getEmail());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setPassword(encodedPassword);
            user.setStatus(EUserStatus.ACTIVE);
            user.setRoles(Collections.singleton(role));

            validateNewRegistration(user);

            return this.userRepository.save(user);
        }catch(Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }


    @Override
    public void validateNewRegistration(UserData user) {
        if (isNotUnique(user)) {
            throw new BadRequestException(String.format("User with email '%s' already exists", user.getEmail()));
        }
    }

   @Override
    public boolean isNotUnique(UserData user) {
        Optional<UserData> userOptional = this.userRepository.findByEmail(user.getEmail());
        return userOptional.isPresent();
    }

}
