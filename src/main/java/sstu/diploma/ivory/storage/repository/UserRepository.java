package sstu.diploma.ivory.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sstu.diploma.ivory.storage.entity.UserEntity;

import java.util.Optional;

@Repository    // TODO: consider @NonNullApi
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);    // TODO: is there a way to speed it up due to @NaturalId?

    boolean existsByName(String name);

    Optional<UserEntity> findByName(String name);
}
