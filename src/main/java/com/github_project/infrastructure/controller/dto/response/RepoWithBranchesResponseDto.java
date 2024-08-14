package com.github_project.infrastructure.controller.dto.response;

import com.github_project.domain.model.Branch;

import java.util.List;

public record RepoWithBranchesResponseDto(String name, String login, List<Branch> branches) {
}

