package com.example.tumblbug.controller;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 상세정보 조회
    @GetMapping("/api/projects/{projectId}")
    public ProjectDetailResponseDto getProject(@PathVariable Long projectId) {
        return projectService.getProject(projectId);
    }

}

