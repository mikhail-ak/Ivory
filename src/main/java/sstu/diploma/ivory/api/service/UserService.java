package sstu.diploma.ivory.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import sstu.diploma.ivory.api.data.UserData;
import sstu.diploma.ivory.storage.entity.UserEntity;
import sstu.diploma.ivory.storage.repository.UserRepository;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private final ConversionService converter;

    // @PreAuthorize with custom SpEL to enforce account creation business logic (super admin creates admins and basics;
    // admins create basics) or always create with BASIC_ROLE and allow to promote?
    public Optional<Long> saveIfNotExists(UserData data) {
        if (repository.existsByEmail(data.getEmail())) {
            return Optional.empty();
        }
        val entity = converter.convert(data, UserEntity.class);    // why @Nullable?
        val id = repository.save(entity).getId();
        log.info(String.format("A new user %s with ID %d has been created", entity.getName(), id));
        return Optional.of(id);
    }
}
