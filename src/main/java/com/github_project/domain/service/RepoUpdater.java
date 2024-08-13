package com.github_project.domain.service;

import com.github_project.domain.model.Repo;
import com.github_project.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepoUpdater {
    private final GithubRepository githubRepository;

    public RepoUpdater(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public void updateRepo(Long id, Repo newRepo) {
        log.info("updating repo with id: " + id);
        githubRepository.existsById(id);
        githubRepository.updateById(id, newRepo);
    }
}
