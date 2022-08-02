package com.example.tumblbug.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.tumblbug.config.AwsS3Config;
import com.example.tumblbug.dto.UploadResponseDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {AwsS3Config.class, AwsS3Service.class})
public class AwsS3ServiceTest {

    @Autowired
    AwsS3Service awsS3Service;

    @Autowired
    AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    List<String> filenames = new ArrayList<>();

    @Test
    @Order(1)
    @DisplayName("이미지 업로드")
    void upload_image() throws IOException {
        // given
        File file = new File("src/test/resources/images/테스트용 이미지.png");
        MultipartFile multipartFile = new MockMultipartFile("file", "테스트용 이미지", "image/png", new FileInputStream(file));

        // when
        UploadResponseDto uploadResponseDto = awsS3Service.upload(multipartFile);

        // then
        assertNotNull(amazonS3.getObject(bucket, uploadResponseDto.getFilename()));

        System.out.println("uploadResponseDto.getFilename() = " + uploadResponseDto.getFilename());
        filenames.add(uploadResponseDto.getFilename());
    }

    @Test
    @Order(2)
    @DisplayName("업로드한 이미지 삭제")
    void delete_image() {
        // given
        for (String filename : filenames) {
            System.out.println("filename = " + filename);
        }

        // when
        List<String> deletedFilenames = awsS3Service.deleteImages(filenames);

        // then
        assertIterableEquals(filenames, deletedFilenames);

        filenames.clear();
    }

}
