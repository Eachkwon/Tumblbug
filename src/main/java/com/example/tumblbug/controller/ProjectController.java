package com.example.tumblbug.controller;

import com.example.tumblbug.dto.AllProjectResponseDto;
import com.example.tumblbug.dto.ProjectRequesetDto;
import com.example.tumblbug.service.ProjectService;
import com.example.tumblbug.service.S3ImageService;
import com.example.tumblbug.service.S3ThumbnailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final S3ImageService s3ImageService;
    private final S3ThumbnailService s3ThumbnailService;

    @GetMapping("/api/projects")
    public List<AllProjectResponseDto> getAllProjects(@RequestParam String category) {
        return projectService.getAllProjects();
    }

    @PostMapping("/api/projects")
    public void createProject(@RequestBody ProjectRequesetDto projectRequesetDto) {
        projectService.createProject(projectRequesetDto);
    }

    @PostMapping("api/projects/images")
    public List<String> uploadImage(@RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        List<String> imgUrls = s3ImageService.upload(files);
        return imgUrls;
    }

    @DeleteMapping("api/projects/images")
    public ResponseEntity<?> deleteImage(@RequestBody Map<String, String> imgUrlMap) {
        String imgUrl = imgUrlMap.get("imgUrl");
        System.out.println(imgUrl);
        s3ImageService.delete(imgUrl);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    @PostMapping("api/projects/thumbnails")
    public List<String> uploadThumbnail(@RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        List<String> imgUrls = s3ThumbnailService.upload(files);
        return imgUrls;
    }

    @DeleteMapping("api/projects/thumbnails")
    public void deleteThumbnail(@RequestBody Map<String, String> imgUrlMap) {
        String imgUrl = imgUrlMap.get("imgUrl");
        System.out.println(imgUrl);
        s3ThumbnailService.delete(imgUrl);
    }

}
