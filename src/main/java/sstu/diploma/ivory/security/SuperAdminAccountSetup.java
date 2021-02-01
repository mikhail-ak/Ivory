package sstu.diploma.ivory.security;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;
import sstu.diploma.ivory.storage.entity.UserEntity;
import sstu.diploma.ivory.storage.repository.UserRepository;

// @Component -- enable later
public class SuperAdminAccountSetup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        val adminEntity = UserEntity.builder()    // TODO: remove sensitive data from sources
                .email("admin@mail.com")
                .name("Bidon Pomoev")
                .role(RoleEnum.ROLE_ADMIN)
                .password("testpassword")
                .enabled(true).build();
        userRepository.save(adminEntity);
    }
}
