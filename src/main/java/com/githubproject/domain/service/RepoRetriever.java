package com.githubproject.domain.service;

import com.githubproject.domain.model.Repo;
import com.githubproject.domain.repository.GithubRepository;
import com.githubproject.validation.error.exception.RepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@Log4j2
public class RepoRetriever {

    private final GithubRepository githubRepository;

    public RepoRetriever(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public List<Repo> findAll(Pageable pageable) {
        log.info("Retrieving all repositories: ");
        return githubRepository.findAll(pageable);
    }

    public Repo findById(Long id) {
        return githubRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id: " + id + " not found"));
    }

}
