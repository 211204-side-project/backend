package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.dto.AuthNicknameDto;
import com.scope.socialboardweb.dto.AuthPhoneNumberDto;
import com.scope.socialboardweb.dto.AuthAccountIdDto;
import com.scope.socialboardweb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserAuthController",description = "유저정보 중복여부 확인 API")
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    @Autowired
    UserService userService;

    @Operation(summary = "accountId 중복 체크")
    @PostMapping("/accountId")
    Boolean isNotDuplicateAccountId(@RequestBody AuthAccountIdDto authAccountIdDto) {
        return userService.isNotDuplicateAccountId(authAccountIdDto.getAccountId());
    }
    @Operation(summary = "phoneNumber 중복 체크")
    @PostMapping("/phoneNumber")
    Boolean isNotDuplicatePhoneNumber(@RequestBody AuthPhoneNumberDto authPhoneNumberDto) {
        return userService.isNotDuplicatePhoneNumber(authPhoneNumberDto.getPhoneNumber());
    }
    @Operation(summary = "nickname 중복 체크")
    @PostMapping("/nickname")
    Boolean isNotDuplicateNickname(@RequestBody AuthNicknameDto authNicknameDto) {
        return userService.isNotDuplicateNickname(authNicknameDto.getNickname());
    }
}

