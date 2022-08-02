package com.example.tumblbug.controller;

import com.example.tumblbug.dto.UploadResponseDto;
import com.example.tumblbug.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/api/images")
    public UploadResponseDto upload(@RequestPart MultipartFile file) {
        return awsS3Service.upload(file);
    }

    @DeleteMapping("/api/images")
    public void deleteImages(@RequestBody List<String> filenames) {
        awsS3Service.deleteImages(filenames);
    }

}
