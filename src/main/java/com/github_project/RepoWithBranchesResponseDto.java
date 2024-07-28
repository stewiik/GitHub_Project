package com.github_project;

import java.util.List;

public record RepoWithBranchesResponseDto(String name, String login, List<Branch> branchesDtos) {
}

