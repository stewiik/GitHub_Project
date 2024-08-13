package com.github_project.domain.service;

import com.github_project.domain.repository.GithubRepository;
import com.github_project.validation.error.exception.RepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RepoDeleter {
    private final GithubRepository githubRepository;

    public RepoDeleter(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public void deleteRepo(Long id) {
        githubRepository.existsById(id);
        log.info("deleting repo by id: " + id);
        githubRepository.deleteById(id);
    }
}
