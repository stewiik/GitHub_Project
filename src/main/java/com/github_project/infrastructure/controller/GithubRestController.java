package com.github_project.infrastructure.controller;

import com.github_project.domain.model.Repo;
import com.github_project.domain.service.*;
import com.github_project.infrastructure.controller.dto.response.RepoWithBranchesResponseDto;
import com.github_project.infrastructure.controller.dto.request.CreateRepoRequestDto;
import com.github_project.infrastructure.controller.dto.request.PartiallyUpdateRepoRequestDto;
import com.github_project.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import com.github_project.infrastructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github_project.infrastructure.controller.GithubMapper.*;

@RestController
@Log4j2
@AllArgsConstructor
@Validated
public class GithubRestController {
    private final GithubService githubService;
    private final RepoRetriever repoRetriever;
    private final RepoAdder repoAdder;
    private final RepoDeleter repoDeleter;
    private final RepoUpdater repoUpdater;


    @GetMapping(value = "/repositories/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReposWithBranches(@PageableDefault(page = 0, size = 20) Pageable pageable,
                                                  @PathVariable String username,
                                                  @RequestHeader(HttpHeaders.ACCEPT) String header) throws HttpMediaTypeNotAcceptableException {

        if (!MediaType.APPLICATION_JSON_VALUE.equals(header)) {
            log.error("Unsupported Accept header: " + header);
            throw new HttpMediaTypeNotAcceptableException(header);
        }

        repoRetriever.findAll(pageable);

        List<RepoWithBranchesResponseDto> repos = githubService.getRepos(username);
        return ResponseEntity.ok(repos);
    }

    @GetMapping("/database")
    public ResponseEntity<GetAllReposResponseDto> getAllReposFromDb(@PageableDefault(page = 0, size = 30) Pageable pageable) {
        List<Repo> allRepos = repoRetriever.findAll(pageable);
        GetAllReposResponseDto response = mapFromRepoToGetAllReposResponseDto(allRepos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRepoResponseDto> getRepoFromDb(@PathVariable Long id) {
        Repo repo = repoRetriever.findById(id);
        GetRepoResponseDto response = mapFromRepoToGetRepoResponseDto(repo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/repo")
    public ResponseEntity<?> postRepo(@RequestBody @Valid CreateRepoRequestDto repoRequest) {
        Repo repo = mapFromCreateRepoRequestDtoToRepo(repoRequest);
        Repo savedRepo = repoAdder.addRepo(repo);
        CreateRepoResponseDto response = mapFromRepoToCreateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteRepoById(@PathVariable Long id) {
        repoDeleter.deleteRepo(id);
        DeleteRepoResponseDto response = mapFromRepoToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepo(@PathVariable Long id,
                                        @RequestBody @Valid UpdateRepoRequestDto updatedRepoRequest) {
        Repo newRepo = mapFromUpdateRepoRequestDtoToRepo(updatedRepoRequest);
        repoUpdater.updateById(id, newRepo);
        UpdateRepoResponseDto response = mapFromRepoToUpdateRepoResponseDto(newRepo);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateRepoResponseDto> partiallyUpdateRepo(@PathVariable Long id,
                                                                              @RequestBody PartiallyUpdateRepoRequestDto updatedRepoRequest) {
        Repo updatedRepo = mapFromPartiallyUpdateRepoRequestDtoToRepo(updatedRepoRequest);
        Repo savedRepo = repoUpdater.updatePartiallyById(id, updatedRepo);
        PartiallyUpdateRepoResponseDto response = mapFromRepoToPartiallyUpdateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(response);
    }

}
