package com.github_project.controller;

import com.github_project.dto.RepoWithBranchesResponseDto;
import com.github_project.service.GithubService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GithubRestController {
    GithubService githubService;

    public GithubRestController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepoWithBranchesResponseDto>> getReposWithBranches(@PathVariable String username,
                                                                                  @RequestHeader(HttpHeaders.ACCEPT) String header) {

        return ResponseEntity.ok(githubService.getRepos(username));
    }

}
