package sstu.diploma.ivory.api.data;

import lombok.Value;
import sstu.diploma.ivory.security.constant.RoleEnum;

import javax.validation.constraints.*;

@Value
@NotNull
public class UserData {

    @Pattern(regexp = "^[a-zA-Z ]{3,32}$", message = "a name must have only latin alphabetic characters and " +
            "whitespace; its length should be between 3 and 32")
    String name;

    @Email(message = "email is invalid")
    String email;

    @Size(max = Integer.MAX_VALUE)
    byte[] profilePicture;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "only decimal numbers are allowed in passwords; password length boundaries are 5 and 8")
    String password;

    Boolean enabled;

    RoleEnum role;
}
