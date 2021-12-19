package com.scope.socialboardweb.utils.jwt.exception;

public class NoTokenException extends IllegalArgumentException {
    public NoTokenException() {
        super("JWT 토큰이 없습니다.");
    }

    public NoTokenException(String msg) {
        super(msg);
    }
}
