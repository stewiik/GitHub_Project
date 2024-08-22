package com.githubproject.validation.error.handler;

import com.githubproject.validation.error.dto.ErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class UnacceptableHeaderErrorHandler {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        log.warn("HttpMediaTypeNotAcceptableException error while accessing repositories");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(), "Unsupported Accept header. Must be application/json"));
    }
}
