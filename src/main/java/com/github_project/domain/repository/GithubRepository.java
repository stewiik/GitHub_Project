package com.github_project.domain.repository;

import com.github_project.domain.model.Repo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepository extends Repository<Repo, Long> {

    Repo save(Repo repo);

    List<Repo> findAll();

    Optional<Repo> findById(Long id);

    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.owner = :#{#newRepo.owner}, r.name = :#{#newRepo.name} WHERE r.id = :id")
    void updateById(Long id, Repo newRepo);
}
