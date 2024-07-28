package com.github_project.service;

import com.github_project.dto.RepoWithBranchesResponseDto;
import com.github_project.error.UsernameNotFoundException;
import com.github_project.model.Branch;
import com.github_project.model.Commit;
import com.github_project.model.Repo;
import com.github_project.proxy.SampleGithubProxy;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    SampleGithubProxy githubClient;

    public GithubService(SampleGithubProxy githubClient) {
        this.githubClient = githubClient;
    }

    public List<RepoWithBranchesResponseDto> getRepos(String username) {
        try {
            List<Repo> allRepos = githubClient.getAllRepos(username);

            List<RepoWithBranchesResponseDto> response = allRepos.stream()
                    .filter(repo -> !repo.fork())
                    .map(repo -> {
                        List<Branch> allBranches = githubClient.getBranches(repo.owner().login(), repo.name());
                        List<Branch> branches = allBranches.stream()
                                .map(branch -> new Branch(branch.name(), new Commit(branch.commit().sha())))
                                .collect(Collectors.toList());

                        return new RepoWithBranchesResponseDto(repo.name(), repo.owner().login(), branches);
                    })
                    .collect(Collectors.toList());

            return response;
        } catch (FeignException exception) {
            throw new UsernameNotFoundException("User: " + username + " not found");
        }
    }
}
