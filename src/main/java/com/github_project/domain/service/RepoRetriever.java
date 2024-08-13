package com.github_project.domain.service;

import com.github_project.domain.model.Repo;
import com.github_project.domain.repository.GithubRepository;
import com.github_project.validation.error.exception.RepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepoRetriever {

    private final GithubRepository githubRepository;

    public RepoRetriever(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public List<Repo> findAll() {
        log.info("Retrieving all repositories: ");
        return githubRepository.findAll();
    }

    public Repo findById(Long id) {
        return githubRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id: " + id + " not found"));
    }
}
