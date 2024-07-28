package com.github_project.error;

import com.github_project.controller.GithubRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubRestController.class)
public class UnacceptableHeaderErrorHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponseDto handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        return new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
    }
}
