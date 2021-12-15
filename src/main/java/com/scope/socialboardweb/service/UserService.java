package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User join(User user) {
        return userRepository.save(user);
    }

//    private boolean duplicateUserExist(User user) {
//        return userRepository.findByUserId(user.getUserId()).isPresent();
//    }
}
