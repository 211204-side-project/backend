package com.scope.socialboardweb.controller.test;

import com.scope.socialboardweb.dto.JwtTokenDto;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
