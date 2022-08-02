package com.example.tumblbug.service;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.dto.ProjectRequestDto;
import com.example.tumblbug.dto.ProjectResponseDto;
import com.example.tumblbug.dto.ProjectsByCategoryResponseDto;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.ImageRepository;
import com.example.tumblbug.repository.ProjectRepository;
import com.example.tumblbug.repository.RewardRepository;
import com.example.tumblbug.repository.ThumbnailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final RewardRepository rewardRepository;

    private final ThumbnailRepository thumbnailRepository;

    private final ImageRepository imageRepository;

    // 프로젝트 리스트 조회
    public List<ProjectsByCategoryResponseDto> getProjectsByCategory(String category) {
        List<Project> projects = projectRepository.findAllByCategory(category);
        return projects.stream()
                .map(ProjectsByCategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    // 프로젝트 상세정보 조회
    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project " + projectId + " is not found"));
        return new ProjectDetailResponseDto(project);
    }

    // 프로젝트 생성
    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto, User user) {
        Project project = projectRepository.save(new Project(projectRequestDto, user));
        rewardRepository.saveAll(project.getRewards());
        thumbnailRepository.saveAll(project.getThumbnails());
        imageRepository.saveAll(project.getImages());
        return new ProjectResponseDto(project);
    }


}
