package sstu.diploma.ivory.api.data;

import lombok.Value;
import org.hibernate.validator.constraints.Length;
import sstu.diploma.ivory.security.RoleEnum;
import sstu.diploma.ivory.security.annotation.PasswordConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@NotNull
public class UserData {

    @Length(min = 5, message = "user name has to be at least 5 characters long")    //TODO: needs more constraints
    String name;

    @Email(message = "email is invalid")
    String email;

    @Size(max = Short.MAX_VALUE)    //TODO: determine a reasonable constraint
    byte[] profilePicture;

    @PasswordConstraint
    String password;

    Boolean enabled;

    RoleEnum role;
}
