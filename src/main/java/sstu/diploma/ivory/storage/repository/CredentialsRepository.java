package sstu.diploma.ivory.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sstu.diploma.ivory.storage.entity.CredentialsEntity;

@Repository
public interface CredentialsRepository extends JpaRepository<CredentialsEntity, Long> {

}
