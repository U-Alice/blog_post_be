package rca.ac.year3.security_starter.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rca.ac.year3.security_starter.dto.AdminRegistrationDTO;
import rca.ac.year3.security_starter.dto.UserRegistrationDTO;
import rca.ac.year3.security_starter.models.UserData;
import rca.ac.year3.security_starter.payload.ApiResponse;
import rca.ac.year3.security_starter.security.CustomUserDetailsService;
import rca.ac.year3.security_starter.services.IUserService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private CustomUserDetailsService service;
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "*************Welcome to the E BANKING SYSTEM********";
    }

    @PostMapping("/register-admin")
    public ResponseEntity<ApiResponse> addNewUser(@RequestBody AdminRegistrationDTO userData) {
        try {
            return ResponseEntity.ok(ApiResponse.success(userService.registerAdmin(userData)));
        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

//TODO : Implement get profile
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @GetMapping("/admin/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserData> listUsers() {
        return service.listAll();
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }


}
