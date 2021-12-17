package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.AuthNicknameDto;
import com.scope.socialboardweb.dto.AuthPhoneNumberDto;
import com.scope.socialboardweb.dto.AuthUserIdDto;
import com.scope.socialboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Boolean isNotDuplicateUserId(String userId) {
        return userRepository.findByUserId(userId).isEmpty();
    }
    public Boolean isNotDuplicatePhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isEmpty();
    }
    public Boolean isNotDuplicateNickname(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }

    public User findUserById(Long id) {

        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
    }

//    private boolean duplicateUserExist(User user) {
//        return userRepository.findByUserId(user.getUserId()).isPresent();
//    }
}
