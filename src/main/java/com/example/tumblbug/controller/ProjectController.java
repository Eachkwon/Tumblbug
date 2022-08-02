package com.example.tumblbug.controller;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.dto.ProjectRequestDto;
import com.example.tumblbug.dto.ProjectsByCategoryResponseDto;
import com.example.tumblbug.security.UserDetailsImpl;
import com.example.tumblbug.service.ProjectService;
import com.example.tumblbug.service.S3ImageService;
import com.example.tumblbug.service.S3ThumbnailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final S3ImageService s3ImageService;
    private final S3ThumbnailService s3ThumbnailService;

    // 프로젝트 리스트 조회
    @GetMapping("/api/projects")
    public List<ProjectsByCategoryResponseDto> getProjects(@RequestParam String category) {
        return projectService.getProjectsByCategory(category);
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

    //본문 이미지 업로드
    @PostMapping("api/projects/images")
    public List<String> uploadImage(@RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        List<String> imgUrls = s3ImageService.upload(files);
        return imgUrls;
    }

    //본문 이미지 업로드 취소
    @DeleteMapping("api/projects/images")
    public ResponseEntity<?> deleteImage(@RequestBody Map<String, String> imgUrlMap) {
        String imgUrl = imgUrlMap.get("imgUrl");
        System.out.println(imgUrl);
        s3ImageService.delete(imgUrl);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    //썸네일 이미지 업로드
    @PostMapping("api/projects/thumbnails")
    public List<String> uploadThumbnail(@RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        List<String> imgUrls = s3ThumbnailService.upload(files);
        return imgUrls;
    }

    //썸네일 이미지 업로드 취소
    @DeleteMapping("api/projects/thumbnails")
    public void deleteThumbnail(@RequestBody Map<String, String> imgUrlMap) {
        String imgUrl = imgUrlMap.get("imgUrl");
        System.out.println(imgUrl);
        s3ThumbnailService.delete(imgUrl);
    }

}
