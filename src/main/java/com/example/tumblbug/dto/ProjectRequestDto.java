package com.example.tumblbug.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class ProjectRequestDto {

    private String category;

    private String summary;

    private String title;

    private List<String> thumbnails;

    private Integer goal;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<RewardRequestDto> rewards;

    private String plan;

    private List<String> images;

    private String creatorName;

    private String creatorBiography;

}
