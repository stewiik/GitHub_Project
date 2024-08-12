package com.github_project.domain.model;

public record Repository(String name, Owner owner, boolean fork) {
}
