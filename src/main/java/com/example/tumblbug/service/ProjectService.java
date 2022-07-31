package com.example.tumblbug.service;

import com.example.tumblbug.dto.AllProjectResponseDto;
import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.dto.ProjectRequesetDto;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<AllProjectResponseDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        return projectList.stream()
                .map(AllProjectResponseDto::new)
                .collect(Collectors.toList());
    }
    
    // 프로젝트 상세정보 조회
    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project " + projectId + " is not found"));
        return new ProjectDetailResponseDto(project);
    }

    public void createProject(ProjectRequesetDto projectRequesetDto) {
        Project project = new Project(projectRequesetDto);
        projectRepository.save(project);
    }    

}