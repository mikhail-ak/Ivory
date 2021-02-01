package sstu.diploma.ivory.storage.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.With;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import sstu.diploma.ivory.security.RoleEnum;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.Collection;

@Getter
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    private String name;

    @NaturalId
    @Column(unique = true)
    private String email;

    @Lob
    private byte[] profilePicture;

    private String password;

    private boolean enabled;

    private RoleEnum role;

    private Instant createdOn;

    private Instant updatedOn;

    @PrePersist
    private void prePersistTime() {
        createdOn = Instant.now();
        updatedOn = Instant.now();
    }

    @PreUpdate
    private void preUpdateTime() {
        updatedOn = Instant.now();
    }
}
