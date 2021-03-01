package sstu.diploma.ivory.security.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:security.properties")
public class SecurityConstants {

    public SecurityConstants(@Value("${jwtSecret}") String secret,
                             @Value("${jwtExpirationTime}") long expirationTime,
                             @Value("${superAdminEmail}") String superAdminEmail,
                             @Value("${superAdminName}") String superAdminName,
                             @Value("${superAdminPassword}") String superAdminPassword) {
        this.secret = secret;
        this.expirationTime = expirationTime;
        this.superAdminEmail = superAdminEmail;
        this.superAdminName = superAdminName;
        this.superAdminPassword = superAdminPassword;
    }

    public final String secret;

    public final long expirationTime;

    public final String superAdminEmail;

    public final String superAdminName;

    public final String superAdminPassword;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HTTP_HEADER_NAME = "Authorization";

    public static final String SIGN_UP_URL = "/user/sign-up";
}