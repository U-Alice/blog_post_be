package qt.rw.challenge.blog_post.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import qt.rw.challenge.blog_post.dto.AdminRegistrationDTO;
import qt.rw.challenge.blog_post.dto.UserRegistrationDTO;
import qt.rw.challenge.blog_post.enums.EGender;
import qt.rw.challenge.blog_post.enums.EUserStatus;
import qt.rw.challenge.blog_post.models.Role;
import qt.rw.challenge.blog_post.models.UserData;
import qt.rw.challenge.blog_post.utils.Profile;


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