package com.scope.socialboardweb.service.exception;

public class WrongUserIdException extends IllegalArgumentException {

    public WrongUserIdException() {
        super("사용자 id가 존재하지 않습니다.");
    }

    public WrongUserIdException(String msg) {
        super(msg);
    }
}
