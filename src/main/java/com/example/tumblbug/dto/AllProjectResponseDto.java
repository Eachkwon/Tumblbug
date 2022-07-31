package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.Thumbnail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AllProjectResponseDto {
    private Long projectId;
    private String category;
    private String summary;
    private String title;


    private List<Thumbnail> thumbnailUrls;

    private Integer goal;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String creatorName;

    private int totalFundingPrice;

    public AllProjectResponseDto(Project project) {
        this.projectId = project.getProjectId();
        this.category = project.getCategory();
        this.summary = project.getSummary();
        this.title = project.getTitle();
        this.thumbnailUrls = project.getThumbnailUrls();
        this.goal = project.getGoal();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.creatorName = project.getCreatorName();
        this.totalFundingPrice = project.getTotalFundingPrice();
    }
}
