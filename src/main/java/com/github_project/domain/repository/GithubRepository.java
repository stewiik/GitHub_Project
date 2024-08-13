package com.github_project.domain.repository;

import com.github_project.domain.model.Repo;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepository extends Repository<Repo, Long> {

    Repo save(Repo repo);

    List<Repo> findAll();

    Optional<Repo> findById(Long id);

}
