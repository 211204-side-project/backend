package com.scope.socialboardweb.utils.jwt.exception;

public class WrongTokenFormatException extends IllegalArgumentException {
    public WrongTokenFormatException() {
        super("JWT 토큰 형식이 잘못되었습니다.");
    }

    public WrongTokenFormatException(String msg) {
        super(msg);
    }
}
