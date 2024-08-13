package com.github_project.domain.service;

import com.github_project.domain.model.Repo;
import com.github_project.infrastructure.controller.dto.RepoWithBranchesResponseDto;
import com.github_project.validation.error.exception.UsernameNotFoundException;
import com.github_project.domain.model.Branch;
import com.github_project.domain.model.Commit;
import com.github_project.domain.model.Repository;
import com.github_project.domain.proxy.SampleGithubProxy;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GithubService {

    private final SampleGithubProxy githubClient;
    private final RepoAdder repoAdder;

    public GithubService(SampleGithubProxy githubClient, RepoAdder repoAdder) {
        this.githubClient = githubClient;
        this.repoAdder = repoAdder;
    }

    public List<RepoWithBranchesResponseDto> getRepos(String username) {
        try {
            List<Repository> allRepos = githubClient.getAllRepos(username);

            return allRepos.stream()
                    .filter(repo -> !repo.fork())
                    .map(repo -> {
                        List<Branch> allBranches = githubClient.getBranches(repo.owner().login(), repo.name());

                        Repo repoToSave = new Repo(repo.owner().login(), repo.name());
                        repoAdder.addRepo(repoToSave);

                        List<Branch> branches = allBranches.stream()
                                .map(branch -> new Branch(branch.name(), new Commit(branch.commit().sha())))
                                .collect(Collectors.toList());

                        return new RepoWithBranchesResponseDto(repo.name(), repo.owner().login(), branches);
                    })
                    .collect(Collectors.toList());
        } catch (FeignException exception) {
            log.error("ERROR: User " + username + " not found");
            throw new UsernameNotFoundException("User: " + username + " not found");
        }
    }
}