package com.github_project.controller;

import com.github_project.dto.RepoWithBranchesResponseDto;
import com.github_project.error.UnacceptableHeaderErrorHandler;
import com.github_project.service.GithubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class GithubRestController {
    GithubService githubService;

    UnacceptableHeaderErrorHandler handler;

    public GithubRestController(GithubService githubService, UnacceptableHeaderErrorHandler handler) {
        this.githubService = githubService;
        this.handler = handler;
    }

    @GetMapping(value = "/repositories/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReposWithBranches(@PathVariable String username,
                                                  @RequestHeader(HttpHeaders.ACCEPT) String header) throws HttpMediaTypeNotAcceptableException {

        if (!MediaType.APPLICATION_JSON_VALUE.equals(header)) {
            log.error("Unsupported Accept header: " + header);
            throw new HttpMediaTypeNotAcceptableException(header);
        }

        List<RepoWithBranchesResponseDto> repos = githubService.getRepos(username);
        return ResponseEntity.ok(repos);
    }
}
