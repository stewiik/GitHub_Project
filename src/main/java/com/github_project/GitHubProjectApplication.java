package com.github_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
public class GitHubProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitHubProjectApplication.class, args);
    }

}
