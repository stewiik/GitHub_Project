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

    public List<Repo> getRepos(String username) {
        List<Repo> allRepos = githubClient.getAllRepos(username);

        List<Repo> noForkRepos = allRepos.stream()
                .filter(repo -> !repo.fork())
                .collect(Collectors.toList());

        return noForkRepos;
    }
}
