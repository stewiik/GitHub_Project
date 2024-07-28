package com.github_project;

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
        List<Repo> allRepos = githubClient.getAllRepos(username);

        List<RepoWithBranchesResponseDto> response = allRepos.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    List<Branch> branches = githubClient.getBranches(repo.owner().login(), repo.name());
                    List<Branch> branchesDtos = branches.stream()
                            .map(branch -> new Branch(branch.name(), new Commit(branch.commit().sha())))
                            .collect(Collectors.toList());

                    return new RepoWithBranchesResponseDto(repo.name(), repo.owner().login(), branchesDtos);
                })
                .collect(Collectors.toList());

        return response;
    }
}
