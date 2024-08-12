package com.github_project.infrastructure.controller;

import com.github_project.domain.model.Repo;
import com.github_project.domain.service.RepoRetriever;
import com.github_project.infrastructure.controller.dto.RepoWithBranchesResponseDto;
import com.github_project.validation.error.handler.UnacceptableHeaderErrorHandler;
import com.github_project.domain.service.GithubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class GithubRestController {
    private final GithubService githubService;

    private final UnacceptableHeaderErrorHandler handler;

    private final RepoRetriever repoRetriever;

    public GithubRestController(GithubService githubService, UnacceptableHeaderErrorHandler handler, RepoRetriever repoRetriever) {
        this.githubService = githubService;
        this.handler = handler;
        this.repoRetriever = repoRetriever;
    }

    @GetMapping(value = "/repositories/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReposWithBranches(@PathVariable String username,
                                                  @RequestHeader(HttpHeaders.ACCEPT) String header) throws HttpMediaTypeNotAcceptableException {

        if (!MediaType.APPLICATION_JSON_VALUE.equals(header)) {
            log.error("Unsupported Accept header: " + header);
            throw new HttpMediaTypeNotAcceptableException(header);
        }

        List<Repo> allRepos = repoRetriever.findAll();

        List<RepoWithBranchesResponseDto> repos = githubService.getRepos(username);
        return ResponseEntity.ok(repos);
    }

    @GetMapping("/database")
    public ResponseEntity<List<Repo>> getInfoFromDb() {
        List<Repo> allRepos = repoRetriever.findAll();
        return ResponseEntity.ok(allRepos);
    }
}
