package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.dto.AuthNicknameDto;
import com.scope.socialboardweb.dto.AuthPhoneNumberDto;
import com.scope.socialboardweb.dto.AuthAccountIdDto;
import com.scope.socialboardweb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserAuthController",description = "유저정보 중복여부 확인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserAuthController {

    private final UserService userService;

    @Operation(summary = "accountId 중복 체크")
    @PostMapping(value = "/accountId", consumes = "application/x-www-form-urlencoded")
    Boolean isNotDuplicateAccountIdByForm(@ModelAttribute AuthAccountIdDto authAccountIdDto) {
        return userService.isNotDuplicateAccountId(authAccountIdDto.getAccountId());
    }

    //JSON 버전 accountId 중복 체크
    @PostMapping(value = "/accountId", consumes = "application/json")
    Boolean isNotDuplicateAccountIdByJson(@RequestBody AuthAccountIdDto authAccountIdDto) {
        return userService.isNotDuplicateAccountId(authAccountIdDto.getAccountId());
    }

    @Operation(summary = "phoneNumber 중복 체크")
    @PostMapping(value = "/phoneNumber", consumes = "application/x-www-form-urlencoded")
    Boolean isNotDuplicatePhoneNumberByForm(@ModelAttribute AuthPhoneNumberDto authPhoneNumberDto) {
        return userService.isNotDuplicatePhoneNumber(authPhoneNumberDto.getPhoneNumber());
    }

    //JSON 버전 phoneNumber 중복 체크
    @PostMapping(value = "/phoneNumber", consumes = "application/json")
    Boolean isNotDuplicatePhoneNumberByJson(@RequestBody AuthPhoneNumberDto authPhoneNumberDto) {
        return userService.isNotDuplicatePhoneNumber(authPhoneNumberDto.getPhoneNumber());
    }

    @Operation(summary = "nickname 중복 체크")
    @PostMapping(value = "/nickname", consumes = "application/x-www-form-urlencoded")
    Boolean isNotDuplicateNicknameByJson(@ModelAttribute AuthNicknameDto authNicknameDto) {
        return userService.isNotDuplicateNickname(authNicknameDto.getNickname());
    }

    //JSON 버전 nickname 중복 체크
    @PostMapping(value = "/nickname", consumes = "application/json")
    Boolean isNotDuplicateNicknameByForm(@RequestBody AuthNicknameDto authNicknameDto) {
        return userService.isNotDuplicateNickname(authNicknameDto.getNickname());
    }
}

