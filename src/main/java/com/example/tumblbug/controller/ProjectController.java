package com.example.tumblbug.controller;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.dto.ProjectRequestDto;
import com.example.tumblbug.dto.ProjectsByCategoryResponseDto;
import com.example.tumblbug.security.UserDetailsImpl;
import com.example.tumblbug.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 리스트 조회
    @GetMapping("/api/projects")
    public Slice<ProjectsByCategoryResponseDto> getProjects(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "all") String category,
            @RequestParam(defaultValue = "popular") String sort,
            @RequestParam(defaultValue = "") String query) {
        return projectService.getProjectsByCategory(page, size, category, sort, query);
    }

    // 프로젝트 상세정보 조회
    @GetMapping("/api/projects/{projectId}")
    public ProjectDetailResponseDto getProject(@PathVariable Long projectId) {
        return projectService.getProjectDetail(projectId);
    }

    // 프로젝트 생성
    @PostMapping("/api/projects")
    public void postProject(@RequestBody @Valid ProjectRequestDto projectRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectService.createProject(projectRequestDto, userDetails.getUser());
    }

}
