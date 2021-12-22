package com.scope.socialboardweb.service.exception;

public class UserNotAuthorizedException extends IllegalArgumentException{

    public UserNotAuthorizedException() {
        super("허가되지 않은 사용자입니다");}

    public UserNotAuthorizedException(String msg) {
        super(msg);
    }
}
