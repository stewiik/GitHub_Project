package com.githubproject.validation.error.dto;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> errors) {
}
