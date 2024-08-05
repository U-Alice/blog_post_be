package rca.ac.year3.security_starter.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.exceptions.BadRequestException;
import rca.ac.year3.security_starter.models.UserData;


@Data
@AllArgsConstructor
public class Profile {
    Object profile;

    public UserData asUser() {
        return (UserData) profile;
    }


    private void is(Erole role) {
        UserData user = (UserData) profile;
        if (user.getRole() != role)
            throw new BadRequestException("You must be a " + role.toString() + " to use this resource ...");
    }

}
