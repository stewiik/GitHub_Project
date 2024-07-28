package com.github_project.error;

import com.github_project.controller.GithubRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubRestController.class)
@Log4j2
public class UnacceptableHeaderErrorHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponseDto handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        log.warn("HttpMediaTypeNotAcceptableException error while accessing repositories");
        return new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
    }
}
