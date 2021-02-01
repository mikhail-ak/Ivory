package sstu.diploma.ivory.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sstu.diploma.ivory.api.data.UserData;
import sstu.diploma.ivory.storage.entity.UserEntity;

@Component
public class UserConverter implements Converter<UserData, UserEntity> {

    @Override
    public UserEntity convert(UserData userData) {
        return UserEntity.builder()
                .email(userData.getEmail())
                .name(userData.getName())
                .role(userData.getRole())
                .password(userData.getPassword())
                .enabled(userData.getEnabled()).build();
    }
}
