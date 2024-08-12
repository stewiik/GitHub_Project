package com.github_project.domain.repository;

import com.github_project.domain.model.Repo;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GithubRepository extends Repository<Repo, Long> {

    Repo save(Repo repo);

    List<Repo> findAll();
}
