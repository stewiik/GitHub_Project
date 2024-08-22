package com.githubproject.domain.model;

public record Repository(String name, Owner owner, boolean fork) {
}
