package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.*;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.service.UserService;
import com.scope.socialboardweb.utils.annotation.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    /**
     * 회원가입:
     * 요청 msg의 헤더 부분이 application/json 일 때,
     * 매핑
     */
    @PostMapping(value = "/signup", consumes = "application/json")
    Boolean signupByJson(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.signup(userRequestDto);
        return true;
    }

    /**
     * 회원가입:
     * 요청 msg의 Content-Type 헤더가 application/x-www-form-urlencoded 일 때,
     * 매핑
     */
    @PostMapping(value = "/signup", consumes = "application/x-www-form-urlencoded")
    Boolean signupByForm(@ModelAttribute UserRequestDto userRequestDto) {
        User user = userService.signup(userRequestDto);
        return true;
    }

    /**
     * 로그인:
     * 요청 msg의 Content-Type 헤더가 application/json 일 때,
     * 매핑
     */
    @Operation(summary = "로그인")
    @PostMapping(value = "/signin", consumes = "application/json")
    ResponseDto signinByJson(@RequestBody UserLoginDto userLoginDto) {
        JwtTokenDto tokenDto = userService.login(userLoginDto);
        return new ResponseDto(tokenDto, "Signin succeeded");
    }

    /**
     * 로그인:
     * 요청 msg의 Content-Type 헤더가 application/x-www-form-urlencoded 일 때,
     * 매핑
     */
    @PostMapping(value = "/signin", consumes = "application/x-www-form-urlencoded")
    ResponseDto signinByForm(@ModelAttribute UserLoginDto userLoginDto) {
        JwtTokenDto tokenDto = userService.login(userLoginDto);
        return new ResponseDto(tokenDto, "Signin succeeded");
    }

    @Operation(summary = "유저 정보")
    @GetMapping(value = "/self")
    ResponseDto getUserSelf(@LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto(userRequestDto.getUserEntity());
        return new ResponseDto(userResponseDto, "User's Information");
    }
}
