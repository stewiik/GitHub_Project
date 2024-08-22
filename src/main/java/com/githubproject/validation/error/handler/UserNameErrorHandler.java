package com.githubproject.validation.error.handler;

import com.githubproject.validation.error.dto.ErrorResponseDto;
import com.githubproject.validation.error.exception.UsernameNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.githubproject.infrastructure.controller.GithubRestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubRestController.class)
@Log4j2
public class UserNameErrorHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleUsernameNotFoundException(UsernameNotFoundException exception) {
        log.warn("UsernameNotFoundException error while accessing repositories");
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
