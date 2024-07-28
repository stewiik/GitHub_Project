package com.github_project.dto;

import com.github_project.model.Branch;

import java.util.List;

public record RepoWithBranchesResponseDto(String name, String login, List<Branch> branchesDtos) {
}

