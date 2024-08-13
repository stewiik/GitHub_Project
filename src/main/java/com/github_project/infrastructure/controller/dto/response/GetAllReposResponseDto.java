package com.github_project.infrastructure.controller.dto.response;

import java.util.List;

public record GetAllReposResponseDto(List<RepoDto> repos) {
}
