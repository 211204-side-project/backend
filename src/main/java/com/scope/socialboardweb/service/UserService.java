package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.*;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.repository.custom.CustomUserRepository;
import com.scope.socialboardweb.service.exception.WrongUserIdException;
import com.scope.socialboardweb.service.exception.WrongUserPasswordException;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    public User signup(User user) {
//        return userRepository.save(user);
//    }
    public User signup(UserRequestDto userRequestDto) {
        User user = User.builder()
                .userId(userRequestDto.getUserId())
                .nickname(userRequestDto.getNickname())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .phoneNumber(userRequestDto.getPhoneNumber())
                .userImgUrl(userRequestDto.getUserImgUrl())
                .isVerifiedEmail(false)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public JwtTokenDto login(UserLoginDto userLoginDto) {
        String userId = userLoginDto.getUserId();
        String password = userLoginDto.getPassword();

        //아이디 먼저 확인
        User userByUserId = checkLoginId(userId);

        if (passwordEncoder.matches(password, userByUserId.getPassword()))
            return new JwtTokenDto(createToken(userByUserId));

        throw new WrongUserPasswordException();
    }

//    private boolean duplicateUserExistByUserId(User user) {
//        return userRepository.existsByUserId(user.getUserId());
//    }

    private User checkLoginId(String userId) {
        User user = customUserRepository.findByUserId(userId).orElseThrow(
                () -> new WrongUserIdException()
        );
        return user;
    }

    private List<User> checkLoginPassword(String password) {
        List<User> userList = customUserRepository.findByPassword(password);
        if (userList.isEmpty()) {
            throw new WrongUserPasswordException();
        }
        return userList;
    }

    private String createToken(User user) {
        return tokenProvider.createJwtToken(user.getId());
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

        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
    }

//    private boolean duplicateUserExist(User user) {
//        return userRepository.findByUserId(user.getUserId()).isPresent();
//    }

}
