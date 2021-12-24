package com.scope.socialboardweb.controller.advice;

import com.scope.socialboardweb.dto.exception.ErrorResponseDto;
import com.scope.socialboardweb.dto.exception.LoginFailStatus;
import com.scope.socialboardweb.service.exception.WrongAccountIdException;
import com.scope.socialboardweb.service.exception.WrongUserPasswordException;
import com.scope.socialboardweb.utils.jwt.exception.NoTokenException;
import com.scope.socialboardweb.utils.jwt.exception.WrongTokenFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserRestControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongAccountIdException.class)
    public ErrorResponseDto wrongAccountIdHandle(WrongAccountIdException e) {
        return new ErrorResponseDto(LoginFailStatus.WRONG_ID, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongUserPasswordException.class)
    public ErrorResponseDto wrongUserPasswordHandle(WrongUserPasswordException e) {
        return new ErrorResponseDto(LoginFailStatus.WRONG_PASSWORD, e.getMessage());
    }

}
