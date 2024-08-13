package com.github_project.infrastructure.controller;

import com.github_project.domain.model.Repo;
import com.github_project.infrastructure.controller.dto.request.CreateRepoRequestDto;
import com.github_project.infrastructure.controller.dto.request.PartiallyUpdateRepoRequestDto;
import com.github_project.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import com.github_project.infrastructure.controller.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GithubMapper {

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.name());
    }

    public static CreateRepoResponseDto mapFromRepoToCreateRepoResponseDto(Repo repo) {
        RepoDto savedRepoDto = GithubMapper.mapFromRepoToRepoDto(repo);
        return new CreateRepoResponseDto(savedRepoDto);
    }

    private static RepoDto mapFromRepoToRepoDto(Repo repo) {
        return new RepoDto(repo.getId(), repo.getOwner(), repo.getName());
    }

    public static GetAllReposResponseDto mapFromRepoToGetAllReposResponseDto(List<Repo> repos) {
        List<RepoDto> repoDtos = repos.stream()
                .map(GithubMapper::mapFromRepoToRepoDto)
                .toList();
        return new GetAllReposResponseDto(repoDtos);
    }

    public static GetRepoResponseDto mapFromRepoToGetRepoResponseDto(Repo repo) {
        RepoDto repoDto = GithubMapper.mapFromRepoToRepoDto(repo);
        return new GetRepoResponseDto(repoDto);
    }

    public static DeleteRepoResponseDto mapFromRepoToDeleteRepoResponseDto(Long id) {
        return new DeleteRepoResponseDto("You deleted repo with id: " + id, HttpStatus.OK);
    }

    public static Repo mapFromUpdateRepoRequestDtoToRepo(UpdateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.name());
    }

    public static UpdateRepoResponseDto mapFromRepoToUpdateRepoResponseDto(Repo newRepo) {
        return new UpdateRepoResponseDto(newRepo.getOwner(), newRepo.getName());
    }

    public static Repo mapFromPartiallyUpdateRepoRequestDtoToRepo(PartiallyUpdateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.name());
    }

    public static PartiallyUpdateRepoResponseDto mapFromRepoToPartiallyUpdateRepoResponseDto(Repo repo) {
        RepoDto repoDto = GithubMapper.mapFromRepoToRepoDto(repo);
        return new PartiallyUpdateRepoResponseDto(repoDto);
    }
}
