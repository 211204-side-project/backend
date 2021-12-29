package com.scope.socialboardweb.controller.test;

import com.scope.socialboardweb.dto.JwtTokenDto;
import com.scope.socialboardweb.dto.ResponseDto;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.utils.annotation.LoginUser;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "JWT 테스트용 API")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class JwtTestController {

    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "테스트 토큰 발급")
    @GetMapping("/token")
    public JwtTokenDto createToken() {
        String tokenValue = jwtTokenProvider.createJwtToken(1L);
        JwtTokenDto tokenDto = new JwtTokenDto(tokenValue);
        return tokenDto;
    }

    /**
     * @RequestAttribute("loginUser") 나
     * @LoginUser 모두 동일한 기능을 한다.
     */
    @Operation(summary = "헤더로 전달된 토큰에서, 유저 정보 뽑아 응답")
    @GetMapping("/user")
    ResponseDto test(@LoginUser @Parameter(hidden = true) UserRequestDto userRequestDto) {
        log.info("Login User Entity: {}", userRequestDto.getUserEntity());
        log.info("Login User AccountId: {}", userRequestDto.getAccountId());
        log.info("Login User Nickname: {}", userRequestDto.getNickname());
        log.info("Login User Password: {}", userRequestDto.getPassword());
        return new ResponseDto(userRequestDto, "전달받은 토큰으로 가져온 유저");
    }

}
