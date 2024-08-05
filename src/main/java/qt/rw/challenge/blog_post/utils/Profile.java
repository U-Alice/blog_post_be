package qt.rw.challenge.blog_post.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import qt.rw.challenge.blog_post.enums.Erole;
import qt.rw.challenge.blog_post.exceptions.BadRequestException;
import qt.rw.challenge.blog_post.models.UserData;


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
