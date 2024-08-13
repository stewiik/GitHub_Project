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
    private final RepoRetriever repoRetriever;

    public RepoUpdater(GithubRepository githubRepository, RepoRetriever repoRetriever) {
        this.githubRepository = githubRepository;
        this.repoRetriever = repoRetriever;
    }

    public void updateById(Long id, Repo repoFromRequest) {
        log.info("updating repo with id: " + id);
        repoRetriever.findById(id);
        githubRepository.updateById(id, repoFromRequest);
    }

    public Repo updatePartiallyById(Long id, Repo repoFromRequest) {
        Repo repoFromDatabase = repoRetriever.findById(id);
        Repo.RepoBuilder builder = Repo.builder();
        if (repoFromRequest.getOwner() != null) {
            builder.owner(repoFromRequest.getOwner());
        } else {
            builder.owner(repoFromDatabase.getOwner());
        }
        if (repoFromRequest.getName() != null) {
            builder.name(repoFromRequest.getName());
        } else {
            builder.name(repoFromDatabase.getName());
        }
        Repo toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }
}
