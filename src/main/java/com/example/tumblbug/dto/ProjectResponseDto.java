package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Image;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.Thumbnail;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProjectResponseDto {

    private Long projectId;

    private String category;

    private String summary;

    private String title;

    private List<String> thumbnails;

    private Integer goal;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<RewardResponseDto> rewards;

    private String plan;
    
    private List<String> images;

    private String creatorName;

    private String creatorBiography;

    private int totalFundingPrice;

    private int fundingCount;

    public ProjectResponseDto(Project project) {
        this.projectId = project.getProjectId();
        this.category = project.getCategory();
        this.summary = project.getSummary();
        this.title = project.getTitle();
        this.thumbnails = project.getThumbnails().stream()
                .map(Thumbnail::getUrl)
                .collect(Collectors.toList());
        this.goal = project.getGoal();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.rewards = project.getRewards().stream()
                .map(RewardResponseDto::new)
                .collect(Collectors.toList());
        this.plan = project.getPlan();
        this.images = project.getImages().stream()
                .map(Image::getUrl)
                .collect(Collectors.toList());
        this.creatorName = project.getCreatorName();
        this.creatorBiography = project.getCreatorBiography();
        this.totalFundingPrice = project.getTotalFundingPrice();
        this.fundingCount = project.getFundingCount();
    }

}
