package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.*;
import com.scope.socialboardweb.service.UserService;
import com.scope.socialboardweb.utils.annotation.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserController",description = "유저 관련 API requestmapping 삭제 버전")
@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    /**
     * 회원가입:
     * 요청 msg의 헤더 부분이 application/json 일 때,
     * 매핑
     */
    @Operation(summary = "User 회원가입, json 형식")
    @PostMapping(value = "/api/user/signup", consumes = "application/json")
    Boolean signupByJson(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.signup(userRequestDto);
        return true;
    }

    /**
     * 회원가입:
     * 요청 msg의 Content-Type 헤더가 application/x-www-form-urlencoded 일 때,
     * 매핑
     */
    @Operation(summary = "User 회원가입, form 형식")
    @PostMapping(value = "/api/user/signup", consumes = "application/x-www-form-urlencoded")
    Boolean signupByForm(@ModelAttribute UserRequestDto userRequestDto) {
        User user = userService.signup(userRequestDto);
        return true;
    }

    /**
     * 로그인:
     * 요청 msg의 Content-Type 헤더가 application/json 일 때,
     * 매핑
     */
    @Operation(summary = "User 로그인, json 형식")
    @PostMapping(value = "/api/user/signin", consumes = "application/json")
    ResponseDto signinByJson(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return new ResponseDto(loginResponseDto, "Signin succeeded");
    }

    /**
     * 로그인:
     * 요청 msg의 Content-Type 헤더가 application/x-www-form-urlencoded 일 때,
     * 매핑
     */
    @Operation(summary = "User 로그인, form 형식")
    @PostMapping(value = "/api/user/signin", consumes = "application/x-www-form-urlencoded")
    ResponseDto signinByForm(@ModelAttribute LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return new ResponseDto(loginResponseDto, "Signin succeeded");
    }

    @Operation(summary = "유저 정보")
    @GetMapping(value = "/api/user/self")
    ResponseDto getUserSelf(@LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto(userRequestDto.getUserEntity());
        return new ResponseDto(userResponseDto, "User's Information");
    }
}
