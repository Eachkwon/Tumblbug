package com.example.tumblbug.service;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    // 프로젝트 상세정보 조회
    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project " + projectId + " is not found"));
        return new ProjectDetailResponseDto(project);
    }

}
