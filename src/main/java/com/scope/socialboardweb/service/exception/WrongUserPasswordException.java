package com.scope.socialboardweb.service.exception;

public class WrongUserPasswordException extends IllegalArgumentException {

    public WrongUserPasswordException() {
        super("사용자 비밀번호가 존재하지 않습니다.");
    }

    public WrongUserPasswordException(String msg) {
        super(msg);
    }
}
