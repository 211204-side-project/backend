package com.scope.socialboardweb.controller.test;

import com.scope.socialboardweb.dto.ResponseDto;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.utils.annotation.RequestAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test")
@RestController
public class GetUserFromTokenTestController {

    @PostMapping("/user")
    ResponseDto test(@RequestAttribute("loginUser") UserRequestDto userRequestDto) {
        log.info("Login User Id: {}", userRequestDto.getUserId());
        log.info("Login User Nickname: {}", userRequestDto.getNickname());
        log.info("Login User Password: {}", userRequestDto.getPassword());
        return new ResponseDto(userRequestDto, "전달받은 토큰으로 가져온 유저");
    }
}
