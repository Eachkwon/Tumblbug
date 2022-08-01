package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.Thumbnail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectsByCategoryResponseDto {

    private Long projectId;

    private String category;

    private String summary;

    private String title;

    private List<Thumbnail> thumbnails;

    private Integer goal;

    private LocalDate startDate;

    private LocalDate endDate;

    private String creatorName;

    private int totalFundingPrice;

    public ProjectsByCategoryResponseDto(Project project) {
        this.projectId = project.getProjectId();
        this.category = project.getCategory();
        this.summary = project.getSummary();
        this.title = project.getTitle();
        this.thumbnails = project.getThumbnails();
        this.goal = project.getGoal();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.creatorName = project.getCreatorName();
        this.totalFundingPrice = project.getTotalFundingPrice();
    }

}
