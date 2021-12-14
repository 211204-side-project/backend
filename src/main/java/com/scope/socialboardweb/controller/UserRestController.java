package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.dto.UserResponseDto;
import com.scope.socialboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    UserResponseDto signup(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = new UserResponseDto();
        boolean b = true;
        try {
            User user = new User();
            user.setUserId(requestDto.getUserId());
            user.setPassword(requestDto.getPassword());
            user.setPhoneNumber(requestDto.getPhoneNumber());
            user.setNickname(requestDto.getNickname());
            if (requestDto.getUserImgUrl() != null) user.setUserImgUrl(requestDto.getUserImgUrl());
            userRepository.save(user);
        } catch(Exception e) {
            b = false;
        }
        responseDto.setCreateAccountMessage(b ? "user signed up successfully" : "user signup failed");
        return responseDto;
    }
}
