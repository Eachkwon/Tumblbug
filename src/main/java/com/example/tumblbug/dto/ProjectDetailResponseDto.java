package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.Thumbnail;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProjectDetailResponseDto {

    private String category;

    private String summary;

    private String title;

    private List<Thumbnail> thumbnails;

    private Integer goal;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<RewardResponseDto> rewards;

    private String plan;

    private String creatorName;

    private String creatorBiography;

    private int totalFundingPrice;

    private int fundingCount;

    public ProjectDetailResponseDto(Project project) {
        this.category = project.getCategory();
        this.summary = project.getSummary();
        this.title = project.getTitle();
//        this.thumbnails = project.getThumbnails();
        this.goal = project.getGoal();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.rewards = project.getRewards().stream()
                .map(RewardResponseDto::new)
                .collect(Collectors.toList());
        this.plan = project.getPlan();
        this.creatorName = project.getCreatorName();
        this.creatorBiography = project.getCreatorBiography();
        this.totalFundingPrice = project.getTotalFundingPrice();
        this.fundingCount = project.getFundingCount();
    }

}
