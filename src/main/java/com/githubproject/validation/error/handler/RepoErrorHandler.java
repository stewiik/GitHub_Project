package com.githubproject.validation.error.handler;

import com.githubproject.infrastructure.controller.GithubRestController;
import com.githubproject.validation.error.exception.RepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import com.githubproject.validation.error.dto.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubRestController.class)
@Log4j2
public class RepoErrorHandler {

    @ExceptionHandler(RepoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleRepoNotFoundException(RepoNotFoundException exception) {
        log.warn("RepoNotFoundException error while accessing repo");
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
