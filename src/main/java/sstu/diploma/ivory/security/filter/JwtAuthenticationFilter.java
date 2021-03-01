package sstu.diploma.ivory.security.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sstu.diploma.ivory.api.data.LoginData;
import sstu.diploma.ivory.security.constant.SecurityConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static sstu.diploma.ivory.security.constant.SecurityConstants.TOKEN_PREFIX;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final SecurityConstants cnst;

    private final ThreadLocal<String> password = new ThreadLocal<>();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            LoginData creds = new ObjectMapper().readValue(req.getInputStream(), LoginData.class);
            val tkn = new UsernamePasswordAuthenticationToken(creds.getName(), creds.getPassword(), new ArrayList<>());
            password.set(creds.getPassword());
            return authenticationManager.authenticate(tkn);
        } catch (IOException e) {
            throw new SecurityException("Failed to read authentication data", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + cnst.expirationTime))
                .withClaim("pwd", password.get())
                .sign(HMAC512(cnst.secret.getBytes()));
        System.out.println("Got creds " + password.get());
        res.addHeader(SecurityConstants.HTTP_HEADER_NAME, TOKEN_PREFIX + token);
    }
}
