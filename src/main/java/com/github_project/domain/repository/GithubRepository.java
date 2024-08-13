package com.github_project.domain.repository;

import com.github_project.domain.model.Repo;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepository extends Repository<Repo, Long> {

    Repo save(Repo repo);

    @Query("SELECT r FROM Repo r")
    List<Repo> findAll();

    @Query("SELECT r FROM Repo r WHERE r.id = :id")
    Optional<Repo> findById(Long id);

    @Modifying
    @Query("DELETE FROM Repo r WHERE r.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.owner = :#{#newRepo.owner}, r.name = :#{#newRepo.name} WHERE r.id = :id")
    void updateById(Long id, Repo newRepo);

    boolean existsById(Long id);
}
