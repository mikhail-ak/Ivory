package sstu.diploma.ivory.storage.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import sstu.diploma.ivory.security.constant.RoleEnum;
import sstu.diploma.ivory.storage.entity.mixin.TimeTrack;
import sstu.diploma.ivory.storage.entity.mixin.TimeTracked;
import sstu.diploma.ivory.storage.listener.TimeTrackListener;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(TimeTrackListener.class)
public class UserEntity implements TimeTracked {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @NaturalId
    @Column(name = "email", unique = true)
    private String email;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "role")
    private RoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<CredentialsEntity> credentials;

    @Embedded
    private TimeTrack timeTrack;

    public void addCredentials(CredentialsEntity credentialsEntity) {
        credentials.add(credentialsEntity);
        credentialsEntity.setUser(this);
    }

    public void removeCredentials(CredentialsEntity credentialsEntity) {
        credentials.remove(credentialsEntity);
        credentialsEntity.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && name.equals(that.name) && email.equals(that.email) && Arrays.equals(profilePicture, that.profilePicture) && passwordHash.equals(that.passwordHash) && enabled.equals(that.enabled) && role == that.role && Objects.equals(credentials, that.credentials) && Objects.equals(timeTrack, that.timeTrack);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, passwordHash, enabled, role, credentials, timeTrack);
        result = 31 * result + Arrays.hashCode(profilePicture);
        return result;
    }
}
