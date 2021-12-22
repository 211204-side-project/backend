package com.scope.socialboardweb.controller.test;

import com.scope.socialboardweb.dto.ResponseDto;
import com.scope.socialboardweb.dto.UserLoginDto;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.utils.annotation.LoginUser;
import com.scope.socialboardweb.utils.annotation.RequestAttribute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/test")
@RestController
public class GetUserFromTokenTestController {

    /**
     * @RequestAttribute("loginUser") 나
     * @LoginUser 모두 동일한 기능을 한다.
     */
    @Operation(description = "헤더로 전달된 토큰에서, 유저 정보 뽑아 응답")
    @GetMapping("/user")
    ResponseDto test(
//        @RequestAttribute("loginUser") @Parameter(hidden = true) UserRequestDto userRequestDto
        @LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto
        ) {
        log.info("Login User Id: {}", userRequestDto.getUserId());
        log.info("Login User Nickname: {}", userRequestDto.getNickname());
        log.info("Login User Password: {}", userRequestDto.getPassword());
        return new ResponseDto(userRequestDto, "전달받은 토큰으로 가져온 유저");
    }
}
