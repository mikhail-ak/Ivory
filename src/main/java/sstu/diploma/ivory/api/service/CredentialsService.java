package sstu.diploma.ivory.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sstu.diploma.ivory.api.data.CredentialsData;
import sstu.diploma.ivory.exception.IvoryAccountException;
import sstu.diploma.ivory.exception.IvorySecurityException;
import sstu.diploma.ivory.storage.entity.CredentialsEntity;
import sstu.diploma.ivory.storage.entity.UserEntity;
import sstu.diploma.ivory.storage.repository.CredentialsRepository;
import sstu.diploma.ivory.storage.repository.UserRepository;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class CredentialsService {

    private final UserRepository userRepository;

    private final CredentialsRepository credentialsRepository;

    private final ConversionService converter;

    @Transactional
    public long saveCredentials(CredentialsData credentials, String name) {
        if (!userRepository.existsByName(name)) {
            throw new IvorySecurityException("There is no user with such name");
        }
        val user = userRepository.findByName(name)
                .orElseThrow(() -> new IvoryAccountException("Failed to load a user with name " + name));
        val credentialsEntity = converter.convert(credentials, CredentialsEntity.class);
        Objects.requireNonNull(credentialsEntity);
        val credentialsId = credentialsRepository.save(credentialsEntity).getId();
        user.addCredentials(credentialsEntity);
        userRepository.save(user);
        log.info("Saved credentials for user " + name + " with metadata " + credentials.getMetadata() + " " + credentials.getPassword());
        return credentialsId;
    }

    public Set<CredentialsData> findAllForUserName(String name) {
        if (!userRepository.existsByName(name)) {
            throw new IvorySecurityException("There is no user with such name");
        }
        Set<CredentialsEntity> entities = userRepository.findByName(name).map(UserEntity::getCredentials)
                .orElseThrow(() -> new IvoryAccountException("Failed to load a user with name " + name));
        return entities.stream().map(entity -> converter.convert(entity, CredentialsData.class)).collect(Collectors.toSet());
    }
}
