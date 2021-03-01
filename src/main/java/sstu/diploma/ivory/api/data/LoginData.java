package sstu.diploma.ivory.api.data;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class LoginData {

    @Pattern(regexp = "^[a-zA-Z ]{3,32}$", message = "a name must have only latin alphabetic characters and " +
            "whitespace; its length should be between 3 and 32")
    String name;

    String password;
}
