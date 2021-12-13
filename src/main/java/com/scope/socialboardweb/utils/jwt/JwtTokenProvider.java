package com.scope.socialboardweb.utils.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long tokenValidityInMilliseconds;

    //관련 값들은 application.properties에 선언되어 있음
    public JwtTokenProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds
    ) {
        this.secretKey = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    /**
     * JWT 토큰 생성 메서드
     */
    public String createJwtToken(String userId) {
        Date now = new Date();

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // jwt를 사용한다고 헤더에 명시
            .setIssuer("scope") //토큰 발급자(iss) 설정
            .setIssuedAt(now) //토큰 발급 시간(iat) 설정
            .setExpiration(new Date(now.getTime() + tokenValidityInMilliseconds)) // 만료시간 설정
            .claim("userId", userId) //토큰을 받을 사용자의 아이디를 비공개 클레임으로 설정
            .signWith(SignatureAlgorithm.HS512, secretKey) //해싱 알고리즘으로 HS512를 사용하기 때문에, secretKey가 512비트 이상이어야 함
            .compact();
    }


    /**
     * 유효한 JWT 토큰인지 확인하고, 클레임을 파싱하는 메서드
     * null 반환시 유효하지 않은 토큰
     * @param authorizationHeader 헤더 중 Authorization 헤더 값
     * @return 토큰이 정상적인 형태라면, 토큰의 클레임들을 반환
     */
    public Claims parseJwtToken(String authorizationHeader) throws JwtException, IllegalArgumentException {
        validateAuthorizationHeader(authorizationHeader); //Authorization 헤더 값을 전달하여, 올바른 형식인지 확인
        String token = extractToken(authorizationHeader); //실제 토큰값 가져오기

        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }


    /**
     * authorization 헤더가 올바른 형식을 가지고 있는지 확인하는 메서드
     * @param authorizationHeader 요청 메시지의 헤더 중 Token이 담겨있는 Authorization 헤더의 값
     */
    private void validateAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("jwt 토큰 형식이 틀립니다.");
        }
    }

    /**
     * 검증된 토큰의 실제 토큰부분을 추출하는 메서드
     * @param authorizationHeader 검증된 JwtToken
     * @return "Bearer"를 제외한 실제 토큰부분 반환함
     */
    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }

}
