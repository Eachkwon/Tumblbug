package com.example.tumblbug.controller;

import com.example.tumblbug.dto.AllProjectResponseDto;
import com.example.tumblbug.dto.ProjectRequesetDto;
import com.example.tumblbug.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/api/projects")
    public List<AllProjectResponseDto> getAllProjects(@RequestParam String category) {
        return projectService.getAllProjects();
    }

    @PostMapping("/api/projects")
    public void createProject(@RequestBody ProjectRequesetDto projectRequesetDto) {
        projectService.createProject(projectRequesetDto);
    }

}
