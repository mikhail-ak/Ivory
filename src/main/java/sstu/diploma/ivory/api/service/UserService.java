package sstu.diploma.ivory.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sstu.diploma.ivory.api.data.UserData;
import sstu.diploma.ivory.exception.IvoryAccountException;
import sstu.diploma.ivory.exception.IvorySecurityException;
import sstu.diploma.ivory.security.constant.RoleEnum;
import sstu.diploma.ivory.storage.entity.UserEntity;
import sstu.diploma.ivory.storage.repository.UserRepository;

import java.util.Collections;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final ConversionService converter;

    public long register(UserData data) {
        if (repository.existsByEmail(data.getEmail())) {
            throw new IvoryAccountException("A user with this email already exists");
        }
        if (repository.existsByName(data.getName())) {
            throw new IvoryAccountException("A user with this name already exists");
        }
        if (data.getRole() != RoleEnum.ROLE_BASIC) {
            throw new IvorySecurityException("You are not authorized to create user accounts with that type of privileges");
        }

        val entity = converter.convert(data, UserEntity.class);
        Objects.requireNonNull(entity, String.format("Failed to convert %s to %s",
                UserData.class.getName(), UserEntity.class.getName()));
        val id = repository.save(entity).getId();
        log.info(String.format("A new user %s with ID %d has been created", entity.getName(), id));
        return id;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        val entity = repository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
        return new User(entity.getName(), entity.getPasswordHash(), Collections.emptyList());
    }
}
