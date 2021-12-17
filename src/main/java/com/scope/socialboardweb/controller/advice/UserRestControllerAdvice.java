package com.scope.socialboardweb.controller.advice;

import com.scope.socialboardweb.dto.exception.ErrorResponseDto;
import com.scope.socialboardweb.dto.exception.LoginFailStatus;
import com.scope.socialboardweb.service.exception.WrongUserIdException;
import com.scope.socialboardweb.service.exception.WrongUserPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserRestControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongUserIdException.class)
    public ErrorResponseDto wrongUserIdHandle(WrongUserIdException e) {
        return new ErrorResponseDto(LoginFailStatus.WRONG_ID, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongUserPasswordException.class)
    public ErrorResponseDto wrongUserPasswordHandle(WrongUserPasswordException e) {
        return new ErrorResponseDto(LoginFailStatus.WRONG_PASSWORD, e.getMessage());
    }


}
