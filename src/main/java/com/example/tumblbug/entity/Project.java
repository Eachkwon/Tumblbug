package com.example.tumblbug.entity;

import com.example.tumblbug.dto.ProjectRequesetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String category;

    private String summary;

    private String title;

    @OneToMany(mappedBy = "project")
    private List<Thumbnail> thumbnails;

    private Integer goal;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "project")
    private List<Reward> rewards;

    private String plan;

    @OneToMany(mappedBy = "project")
    private List<Image> images;

    private String creatorName;

    private String creatorBiography;

    private int totalFundingPrice;

    private int fundingCount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Project(ProjectRequesetDto projectRequestDto) {
        this.category = projectRequestDto.getCategory();
        this.summary = projectRequestDto.getSummary();
        this.title = projectRequestDto.getTitle();
        this.thumbnailUrls = projectRequestDto.getThumbnailUrls();
        this.goal = projectRequestDto.getGoal();
        this.startDate = projectRequestDto.getStartDate();
        this.endDate = projectRequestDto.getEndDate();
        this.rewards = projectRequestDto.getRewards();
        this.plan = projectRequestDto.getPlan();
        this.images = projectRequestDto.getImages();
        this.creatorName = projectRequestDto.getCreatorName();
        this.creatorBiography = projectRequestDto.getCreatorBiography();
        this.totalFundingPrice = projectRequestDto.getTotalFundingPrice();
        this.fundingCount = projectRequestDto.getFundingCount();
    }
    
    public void addFund(Reward reward) {
        totalFundingPrice += reward.getFundingPrice();
        fundingCount++;
    }

}