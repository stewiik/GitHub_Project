package com.githubproject.domain.proxy;

import com.githubproject.domain.model.Branch;
import com.githubproject.domain.model.Repository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-client")
public interface SampleGithubProxy {

    @GetMapping("/users/{username}/repos")
    List<Repository> getAllRepos(@PathVariable("username") String username);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<Branch> getBranches(@PathVariable("owner") String owner,
                             @PathVariable("repo") String repo);
}
