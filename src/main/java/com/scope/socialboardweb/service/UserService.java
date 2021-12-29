package com.scope.socialboardweb.service;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.*;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.repository.custom.CustomUserRepository;
import com.scope.socialboardweb.service.exception.WrongAccountIdException;
import com.scope.socialboardweb.service.exception.WrongUserPasswordException;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .accountId(userRequestDto.getAccountId())
                .nickname(userRequestDto.getNickname())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .phoneNumber(userRequestDto.getPhoneNumber())
                .userImgUrl(userRequestDto.getUserImgUrl())
                .isVerifiedEmail(false)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String accountId = loginRequestDto.getAccountId();
        String password = loginRequestDto.getPassword();

        //아이디로 유저 확인
        User userByAccountId = checkLoginId(accountId);

        //비밀번호로 유저 확인
        if (passwordEncoder.matches(password, userByAccountId.getPassword()))
            return new LoginResponseDto(createToken(userByAccountId), userByAccountId);

        throw new WrongUserPasswordException();
    }

//    private boolean duplicateUserExistByAccountId(User user) {
//        return userRepository.existsByAccountId(user.getAccountId());
//    }

    private User checkLoginId(String accountId) {
        User user = customUserRepository.findByAccountId(accountId).orElseThrow(
                () -> new WrongAccountIdException()
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

    public Boolean isNotDuplicateAccountId(String accountId) {
        return userRepository.findByAccountId(accountId).isEmpty();
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
//        return userRepository.findByAccountId(user.getAccountId()).isPresent();
//    }

}
