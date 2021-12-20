package com.scope.socialboardweb.utils.db;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitAdminAccount {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        User user = User.builder()
            .userId("admin")
            .nickname("admin")
            .password(passwordEncoder.encode("admin123!"))
            .phoneNumber("010-1234-5678")
            .userImgUrl(null)
            .isVerifiedEmail(false)
            .build();
        userRepository.save(user);
    }
}
