package com.scope.socialboardweb.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    String secretKey = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK";
    long tokenValidityInMilliseconds = 86400L;

    @DisplayName("토큰 생성")
    @Test
    void createToken() {
        //given
        String token;

        //when
        JwtTokenProvider tokenProvider = new JwtTokenProvider(secretKey, tokenValidityInMilliseconds);
        token = tokenProvider.createJwtToken(1L);

        //then
        assertNotNull(token);
    }

    @DisplayName("토큰 유효성 검증 성공")
    @Test
    void validateTokenSuccessCase() {
        //given
        JwtTokenProvider tokenProvider = new JwtTokenProvider(secretKey, tokenValidityInMilliseconds);
        String token = "Bearer " + tokenProvider.createJwtToken(1L);
        Claims claims;

        //when
        claims = tokenProvider.parseJwtToken(token);

        //then
        assertAll(
            () -> {assertEquals(1, claims.get("userPk"));},
            () -> {assertEquals("scope", claims.get("iss"));}
        );
    }

    @DisplayName("토큰 유효성 검증 실패: 올바르지 않은 형식")
    @Test
    void validateTokenFormatFail() {
        //given
        JwtTokenProvider tokenProvider = new JwtTokenProvider(secretKey, tokenValidityInMilliseconds);
        String token = tokenProvider.createJwtToken(1L);
        Claims claims;

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            tokenProvider.parseJwtToken(token);
        });
    }

    @DisplayName("토큰 유효성 검증 실패: 유효시간초과")
    @Test
    void validateTokenExpiredTimeFail() {
        //given
        long tokenValidityInMilliseconds = 0;
        JwtTokenProvider tokenProvider = new JwtTokenProvider(secretKey, tokenValidityInMilliseconds);
        String token = "Bearer " + tokenProvider.createJwtToken(1L);
        Claims claims;

        //then
        assertThrows(ExpiredJwtException.class, () -> {
            tokenProvider.parseJwtToken(token);
        });
    }

}