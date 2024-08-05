package rca.ac.year3.security_starter.security;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rca.ac.year3.security_starter.dto.UserRegistrationDTO;
import rca.ac.year3.security_starter.enums.EUserStatus;
import rca.ac.year3.security_starter.exceptions.BadRequestException;
import rca.ac.year3.security_starter.models.UserData;
import rca.ac.year3.security_starter.repository.UserDataRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDataRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserData loadCurrentUser(String username) throws UsernameNotFoundException {
        Optional<UserData> userDetail = repository.findByEmail(username);
        return userDetail
                .orElseThrow(() -> new UsernameNotFoundException("User not found " +
                        username));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userData = repository.findByEmail(username);
        //Convert userData to UserDetails
        return userData.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    //    public String addUser(UserRegistrationDTO dto) {
//        userData.setPassword(encoder.encode(userData.getPassword()));
//        repository.save(userData);
//        return "User Added Successfully";
//    }
    public List<UserData> listAll() {
        return repository.findAll();
    }
}

