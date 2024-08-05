package rca.ac.year3.security_starter.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rca.ac.year3.security_starter.dto.AdminRegistrationDTO;
import rca.ac.year3.security_starter.dto.UserRegistrationDTO;
import rca.ac.year3.security_starter.enums.EGender;
import rca.ac.year3.security_starter.enums.EUserStatus;
import rca.ac.year3.security_starter.models.Role;
import rca.ac.year3.security_starter.models.UserData;
import rca.ac.year3.security_starter.utils.Profile;


import java.util.UUID;


public interface IUserService {
    UserData findById(UUID id);

    UserData getLoggedInUser();

    Profile getLoggedInProfile();

    UserData getByEmail(String email);

    UserData save(UserData user);


    UserData registerUser(UserRegistrationDTO dto);

    void validateNewRegistration(UserData user);

    boolean isNotUnique(UserData user);

    UserData registerAdmin(AdminRegistrationDTO userData);

//    UserData findById(UUID id);

//    UserData getLoggedInUser();


//    UserData getByEmail(String email);
//
//    boolean isCodeValid(String email, String activationCode);
//
//    UserData save(UserData user);

//    UserData registerAdmin(UserRegistrationDTO dto);
//
//    void validateNewRegistration(UserData user);
//
//    boolean isNotUnique(UserData user);

//    void verifyEmail(String email, String activationCode);
//
//    UserData approve(UUID id);
//
//    UserData reject(UUID id, String rejectionMessage);
//
//    void deActivate(UUID id);
//
//    Page<UserData> search(EUserStatus status, String name, EGender gender, Pageable pageable);
//
//    Page<UserData> search(Role role, EUserStatus status, String name, EGender gender, Pageable pageable);
}