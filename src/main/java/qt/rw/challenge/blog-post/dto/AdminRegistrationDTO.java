package rca.ac.year3.security_starter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rca.ac.year3.security_starter.enums.EGender;

import java.util.List;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationDTO {
    @Email
    private  String email;

    @NotBlank
    private  String firstName;

    @NotBlank
    private  String lastName;
    //TODO:  ADD dob
    //TODO: validation of duplicate email

    @Pattern(regexp = "[0-9]{16}")
    private String nationalId;

    private String key;

    @NotBlank
    @Pattern(regexp = "[0-9]{9,10}", message = "Your phone is not a valid tel we expect 07***")
    private  String mobile;

    private EGender gender;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)."
    )
    String password;

}
