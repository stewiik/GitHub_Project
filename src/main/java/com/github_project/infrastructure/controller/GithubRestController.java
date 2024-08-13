package com.github_project.infrastructure.controller;

import com.github_project.domain.model.Repo;
import com.github_project.domain.service.*;
import com.github_project.infrastructure.controller.dto.RepoWithBranchesResponseDto;
import com.github_project.validation.error.handler.UnacceptableHeaderErrorHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
public class GithubRestController {
    private final GithubService githubService;
    private final UnacceptableHeaderErrorHandler handler;
    private final RepoRetriever repoRetriever;
    private final RepoAdder repoAdder;
    private final RepoDeleter repoDeleter;
    private final RepoUpdater repoUpdater;


    @GetMapping(value = "/repositories/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReposWithBranches(@PageableDefault(page=0, size=15) Pageable pageable,
                                                  @PathVariable String username,
                                                  @RequestHeader(HttpHeaders.ACCEPT) String header) throws HttpMediaTypeNotAcceptableException {

        if (!MediaType.APPLICATION_JSON_VALUE.equals(header)) {
            log.error("Unsupported Accept header: " + header);
            throw new HttpMediaTypeNotAcceptableException(header);
        }

        List<Repo> allRepos = repoRetriever.findAll(pageable);

        List<RepoWithBranchesResponseDto> repos = githubService.getRepos(username);
        return ResponseEntity.ok(repos);
    }

    @GetMapping("/database")
    public ResponseEntity<List<Repo>> getAllReposFromDb(@PageableDefault(page=0, size=15) Pageable pageable) {
        List<Repo> allRepos = repoRetriever.findAll(pageable);
        return ResponseEntity.ok(allRepos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repo> getRepoFromDb(@PathVariable Long id) {
        Repo repo = repoRetriever.findById(id);
        return ResponseEntity.ok(repo);
    }

    @PostMapping("repo")
    public ResponseEntity<Repo> postRepo(@RequestBody @Valid Repo repo) {
        Repo savedRepo = repoAdder.addRepo(repo);
        return ResponseEntity.ok(savedRepo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRepoById(@PathVariable Long id) {
        repoDeleter.deleteRepo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateRepo(@PathVariable Long id,
                                           @RequestBody @Valid Repo updatedRepo) {
        repoUpdater.updateById(id, updatedRepo);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Repo> partiallyUpdateRepo(@PathVariable Long id,
                                                    @RequestBody Repo updatedRepo) {
        Repo savedRepo = repoUpdater.updatePartiallyById(id, updatedRepo);
        return ResponseEntity.ok(savedRepo);
    }

}
