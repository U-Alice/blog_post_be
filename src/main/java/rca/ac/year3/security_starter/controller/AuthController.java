package rca.ac.year3.security_starter.controller;


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
import rca.ac.year3.security_starter.dto.LoginDTO;
import rca.ac.year3.security_starter.exceptions.BadRequestException;
import rca.ac.year3.security_starter.payload.ApiResponse;
import rca.ac.year3.security_starter.payload.JWTAuthenticationResponse;
import rca.ac.year3.security_starter.security.CustomUserDetailsService;
import rca.ac.year3.security_starter.services.JwtService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;

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
//        try {
            log.info("Request to login user {} " , authRequest.getUsername());
            UserDetails userDetails = service.loadUserByUsername(authRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(ApiResponse.success(new JWTAuthenticationResponse(jwtService.generateToken(authRequest.getUsername()))));
            } else {
                throw new UsernameNotFoundException("Invalid user request !");
            }

//        }catch (Exception e){
//            log.info("Error Occurred while logging in user {} ", authRequest.getUsername());
//            if(e instanceof BadCredentialsException){
//                throw new BadRequestException("Incorrect Email or Password!");
//            }
//            ExceptionUtils.handleControllerExceptions(e);
//            return null;
//        }
    }
}
