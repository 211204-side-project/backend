package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.dto.AuthNicknameDto;
import com.scope.socialboardweb.dto.AuthPhoneNumberDto;
import com.scope.socialboardweb.dto.AuthUserIdDto;
import com.scope.socialboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/userId")
    Boolean checkDuplicateUser(@RequestBody AuthUserIdDto authUserIdDto) {
        return userRepository.findByUserId(authUserIdDto.getUserId()).isEmpty();
    }
    @PostMapping("/phoneNumber")
    Boolean checkDuplicatePhone(@RequestBody AuthPhoneNumberDto authPhoneNumberDto) {
        return userRepository.findByPhoneNumber(authPhoneNumberDto.getPhoneNumber()).isEmpty();
    }
    @PostMapping("/nickname")
    Boolean checkDuplicateNickname(@RequestBody AuthNicknameDto authNicknameDto) {
        return userRepository.findByNickname(authNicknameDto.getNickname()).isEmpty();
    }
}

