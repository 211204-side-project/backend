package com.scope.socialboardweb.service.exception;

public class WrongAccountIdException extends IllegalArgumentException {

    public WrongAccountIdException() {
        super("사용자 id가 존재하지 않습니다.");
    }

    public WrongAccountIdException(String msg) {
        super(msg);
    }
}
