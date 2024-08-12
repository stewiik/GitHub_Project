package com.github_project.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "repo")
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String owner;

    @Column(nullable = false)
    String name;

    public Repo() {
    }

    public Repo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public Repo(Long id, String owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }
}
