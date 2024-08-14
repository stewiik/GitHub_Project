package com.github_project.validation.error.handler;

import com.github_project.infrastructure.controller.GithubRestController;
import com.github_project.validation.error.dto.ApiValidationErrorResponseDto;
import com.github_project.validation.error.dto.ErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = GithubRestController.class)
@Log4j2
public class ApiValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiValidationErrorResponseDto handleValidationException(MethodArgumentNotValidException exception) {
        List<String> messages = getErrorsFromException(exception);
        log.warn("MethodArgumentNotValidException error while accessing repo");
        return new ApiValidationErrorResponseDto(messages);
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
