package com.github_project.controller;

import com.github_project.dto.RepoWithBranchesResponseDto;
import com.github_project.service.GithubService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuthubRestController {
    GithubService githubService;

    public GuthubRestController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepoWithBranchesResponseDto>> getReposWithBranches(@PathVariable String username,
                                                                            @RequestParam(HttpHeaders.ACCEPT) String header) {

        return ResponseEntity.ok(githubService.getRepos(username));
    }

}
