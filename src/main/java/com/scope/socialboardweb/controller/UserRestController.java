package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.*;
import com.scope.socialboardweb.repository.UserRepository;
import com.scope.socialboardweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    Boolean signup(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.signup(new User(userRequestDto));
//        return new ResponseDto(user, "true");
        return true;
    }

    @PostMapping("/signin")
    ResponseDto signin(@RequestBody UserLoginDto userLoginDto) {
        JwtTokenDto tokenDto = userService.login(userLoginDto);
        return new ResponseDto(tokenDto, "Signin succeeded");
    }
}
