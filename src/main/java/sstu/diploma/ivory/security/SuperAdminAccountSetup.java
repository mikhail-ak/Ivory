package sstu.diploma.ivory.security;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sstu.diploma.ivory.security.constant.RoleEnum;
import sstu.diploma.ivory.security.constant.SecurityConstants;
import sstu.diploma.ivory.storage.entity.UserEntity;
import sstu.diploma.ivory.storage.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class SuperAdminAccountSetup implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    private final SecurityConstants constants;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        val adminEntity = UserEntity.builder()
                .email(constants.superAdminEmail)
                .name(constants.superAdminName)
                .role(RoleEnum.ROLE_ADMIN)
                .passwordHash(constants.superAdminPassword)
                .enabled(true).build();
        userRepository.save(adminEntity);
    }
}
