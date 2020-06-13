package com.retheviper.springbootsample.api.v1.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.retheviper.springbootsample.api.v1.viewmodel.ExceptionViewModel;
import com.retheviper.springbootsample.common.constant.MemberExceptionMessage;
import com.retheviper.springbootsample.common.exception.MemberException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionViewModel handleMemberException(final MemberException exception) {
        final String message = exception.getMessage();
        final MemberExceptionMessage code = MemberExceptionMessage.getCode(message);
        return new ExceptionViewModel(LocalDateTime.now(), "Member", code.toString(), message);
    }
}
