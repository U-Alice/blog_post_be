package qt.rw.challenge.blog_post.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qt.rw.challenge.blog_post.dto.LoginDTO;
import qt.rw.challenge.blog_post.exceptions.BadRequestException;
import qt.rw.challenge.blog_post.payload.ApiResponse;
import qt.rw.challenge.blog_post.payload.JWTAuthenticationResponse;
import qt.rw.challenge.blog_post.security.CustomUserDetailsService;
import qt.rw.challenge.blog_post.services.JwtService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticateAndGetToken( @Valid @RequestBody LoginDTO authRequest) {
        try {
            log.info("Request to login user {} " , authRequest.getUsername());
            UserDetails userDetails = service.loadUserByUsername(authRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(ApiResponse.success(new JWTAuthenticationResponse(jwtService.generateToken(authRequest.getUsername()))));
            } else {
                throw new UsernameNotFoundException("Invalid user request !");
            }

        }catch (Exception e){
            log.info("Error Occurred while logging in user {} ", authRequest.getUsername());
            if(e instanceof BadCredentialsException){
                throw new BadRequestException("Incorrect Email or Password!");
            }
            ExceptionUtils.handleControllerExceptions(e);
            return null;
        }
    }
}
