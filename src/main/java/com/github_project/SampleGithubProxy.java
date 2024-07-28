package com.github_project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient
public interface SampleGithubProxy {

    @GetMapping("/users/{username}/repos")
    List<Repo> getAllRepos(@PathVariable("username") String username);
}
