package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.dto.AuthNicknameDto;
import com.scope.socialboardweb.dto.AuthPhoneNumberDto;
import com.scope.socialboardweb.dto.AuthUserIdDto;
import com.scope.socialboardweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    @Autowired
    UserService userService;

    @PostMapping("/userId")
    Boolean isNotDuplicateUserId(@RequestBody AuthUserIdDto authUserIdDto) {
        return userService.isNotDuplicateUserId(authUserIdDto.getUserId());
    }
    @PostMapping("/phoneNumber")
    Boolean isNotDuplicatePhoneNumber(@RequestBody AuthPhoneNumberDto authPhoneNumberDto) {
        return userService.isNotDuplicatePhoneNumber(authPhoneNumberDto.getPhoneNumber());
    }
    @PostMapping("/nickname")
    Boolean isNotDuplicateNickname(@RequestBody AuthNicknameDto authNicknameDto) {
        return userService.isNotDuplicateNickname(authNicknameDto.getNickname());
    }
}

