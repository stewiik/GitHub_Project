package com.githubproject.domain.service;

import com.githubproject.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepoDeleter {
    private final GithubRepository githubRepository;
    private final RepoRetriever repoRetriever;

    public RepoDeleter(GithubRepository githubRepository, RepoRetriever repoRetriever) {
        this.githubRepository = githubRepository;
        this.repoRetriever = repoRetriever;
    }

    public void deleteRepo(Long id) {
        repoRetriever.findById(id);
        log.info("deleting repo by id: " + id);
        githubRepository.deleteById(id);
    }
}
