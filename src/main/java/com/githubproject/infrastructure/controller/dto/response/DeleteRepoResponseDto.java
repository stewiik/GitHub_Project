package com.githubproject.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteRepoResponseDto(String message, HttpStatus status) {
}
