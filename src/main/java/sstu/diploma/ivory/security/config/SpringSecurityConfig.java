package sstu.diploma.ivory.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sstu.diploma.ivory.api.service.UserService;
import sstu.diploma.ivory.security.filter.JwtAuthenticationFilter;
import sstu.diploma.ivory.security.filter.JwtAuthorizationFilter;
import sstu.diploma.ivory.security.MyPasswordEncoder;
import sstu.diploma.ivory.security.constant.SecurityConstants;

import static sstu.diploma.ivory.security.constant.SecurityConstants.SIGN_UP_URL;

@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;

    private final SecurityConstants constants;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), constants))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), constants))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {    // TODO actually needed?
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
