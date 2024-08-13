package com.github_project.validation.error.exception;

public class RepoNotFoundException extends RuntimeException {
    public RepoNotFoundException(String message) {
        super(message);
    }
}
