package sstu.diploma.ivory.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return String.valueOf(Objects.hashCode(charSequence));
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
