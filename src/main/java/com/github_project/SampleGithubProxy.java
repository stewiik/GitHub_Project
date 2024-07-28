package com.github_project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-client")
public interface SampleGithubProxy {

    @GetMapping("/users/{username}/repos")
    List<Repo> getAllRepos(@PathVariable("username") String username);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<Branch> getBranches(@PathVariable("owner") String owner,
                             @PathVariable("repo") String repo);
}
