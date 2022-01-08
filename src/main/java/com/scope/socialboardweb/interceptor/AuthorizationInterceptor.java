package com.scope.socialboardweb.interceptor;

import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.repository.custom.CustomUserRepository;
import com.scope.socialboardweb.utils.jwt.JwtTokenProvider;
import com.scope.socialboardweb.utils.jwt.exception.NoTokenException;
import com.scope.socialboardweb.utils.jwt.exception.WrongTokenFormatException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //Token Preflight 처리
        if (isPreflight(request.getMethod())) {
            return true;
        }

        String authorizationValue = request.getHeader("Authorization");
        Claims claims = null;

        try {
            claims = tokenProvider.parseJwtToken(authorizationValue);
        } catch (NoTokenException e) {
            responseError(response, "NO_TOKEN", "JWT 토큰값이 없습니다.");
            return false;
        } catch (WrongTokenFormatException e) {
            responseError(response, "WRONG_TOKEN_FORMAT", "JWT 토큰 형식이 틀립니다.");
            return false;
        } catch (ExpiredJwtException e) {
            responseError(response, "TOKEN_EXPIRED_TIME_OVER", "JWT 토큰의 유효일자가 지났습니다. 서버를 재시작했다면 토큰을 다시 발급받아주세요.");
            return false;
        } catch (JwtException e) {
            responseError(response, "WRONG_TOKEN", "JWT 토큰의 값이 잘못되었습니다.");
            return false;
        }

        Long userPk = Long.parseLong(String.valueOf((int) claims.get("userPk")));
        Optional<User> requestUser = customUserRepository.findById(userPk);

        //해당 pk값을 갖는 유저가 없을 때
        if (requestUser.isEmpty()) {
            responseError(response, "WRONG_USER_PK_VALUE", "잘못된 사용자 pk값이 Token Claim에 포함되어 있습니다.");
            return false;
        }

        request.setAttribute("loginUser", new UserRequestDto(requestUser.get()));
        return true;
    }

    private boolean isPreflight(String httpMethod) {
        if (httpMethod.equals("OPTIONS")) {
            return true;
        }
        return false;
    }

    private void responseError(HttpServletResponse response, String status, String msg) throws IOException {
        response.setStatus(400);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
            "{" +
                "\"status\": \"" + status + "\",\n" +
                "\"msg\": \"" + msg + "\"" +
                "}"
        );
    }

}
