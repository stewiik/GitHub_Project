package com.githubproject.domain.service;

import com.githubproject.domain.model.Repo;
import com.githubproject.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepoAdder {
    private final GithubRepository githubRepository;

    public RepoAdder(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public Repo addRepo(Repo repo) {
        log.info("adding new repo: " + repo);
        return githubRepository.save(repo);
    }
}
