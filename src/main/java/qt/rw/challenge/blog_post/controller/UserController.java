package qt.rw.challenge.blog_post.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import qt.rw.challenge.blog_post.dto.AdminRegistrationDTO;
import qt.rw.challenge.blog_post.dto.UserRegistrationDTO;
import qt.rw.challenge.blog_post.models.UserData;
import qt.rw.challenge.blog_post.payload.ApiResponse;
import qt.rw.challenge.blog_post.security.CustomUserDetailsService;
import qt.rw.challenge.blog_post.services.IUserService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;
import qt.rw.challenge.blog_post.utils.Profile;

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
        return "*************Welcome to the QT BLOG APP********";
    }

    @PostMapping("/register-admin")
    public ResponseEntity<ApiResponse> addNewUser(@RequestBody AdminRegistrationDTO userData) {
        try {
            return ResponseEntity.ok(ApiResponse.success(userService.registerAdmin(userData)));
        }catch (Exception e){
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

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

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(){
        Profile profile = userService.getLoggedInProfile();
        return ResponseEntity.ok(ApiResponse.success(profile));
    }

}
