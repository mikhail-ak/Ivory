package sstu.diploma.ivory.storage.entity;

import lombok.*;
import sstu.diploma.ivory.security.annotation.Encrypted;
import sstu.diploma.ivory.storage.entity.mixin.TimeTrack;
import sstu.diploma.ivory.storage.entity.mixin.TimeTracked;
import sstu.diploma.ivory.storage.listener.TimeTrackListener;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credentials")
@EntityListeners(TimeTrackListener.class)
public class CredentialsEntity implements TimeTracked {

    @Id
    @GeneratedValue
    @Column(name = "credentials_id")
    private Long id;

    @Column(name = "metadata")
    private String metadata;

    @Encrypted
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Embedded
    private TimeTrack timeTrack;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialsEntity that = (CredentialsEntity) o;
        return id.equals(that.id) && metadata.equals(that.metadata) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, metadata, password);
    }
}
