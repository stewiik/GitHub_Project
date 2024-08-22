package com.githubproject.infrastructure.controller.dto.response;

import com.githubproject.domain.model.Branch;

import java.util.List;

public record RepoWithBranchesResponseDto(String name, String login, List<Branch> branches) {
}

