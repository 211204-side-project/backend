package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.dto.AuthNicknameDto;
import com.scope.socialboardweb.dto.AuthPhoneNumberDto;
import com.scope.socialboardweb.dto.AuthUserIdDto;
import com.scope.socialboardweb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "UserAuthController",description = "중복 체크")
public class UserAuthController {
    @Autowired
    UserService userService;

    @Operation(summary = "아이디 중복 체크")
    @PostMapping("/userId")
    Boolean isNotDuplicateUserId(@RequestBody AuthUserIdDto authUserIdDto) {
        return userService.isNotDuplicateUserId(authUserIdDto.getUserId());
    }
    @Operation(summary = "전화번호 중복 체크")
    @PostMapping("/phoneNumber")
    Boolean isNotDuplicatePhoneNumber(@RequestBody AuthPhoneNumberDto authPhoneNumberDto) {
        return userService.isNotDuplicatePhoneNumber(authPhoneNumberDto.getPhoneNumber());
    }

    @Operation(summary = "닉네임 중복 체크")
    @PostMapping("/nickname")
    Boolean isNotDuplicateNickname(@RequestBody AuthNicknameDto authNicknameDto) {
        return userService.isNotDuplicateNickname(authNicknameDto.getNickname());
    }
}

