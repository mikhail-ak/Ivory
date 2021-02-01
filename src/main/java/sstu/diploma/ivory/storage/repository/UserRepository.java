package sstu.diploma.ivory.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sstu.diploma.ivory.storage.entity.UserEntity;

@Repository    // TODO: consider @NonNullApi
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);    // TODO: is there a way to speed it up due to @NaturalId?
}
