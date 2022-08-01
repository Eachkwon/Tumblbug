package com.example.tumblbug.entity;

import com.example.tumblbug.dto.ProjectRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String category;

    private String summary;

    private String title;

    @ElementCollection
    private List<String> thumbnails = new ArrayList<>();

    private Integer goal;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "project")
    private List<Reward> rewards;

    private String plan;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private String creatorName;

    private String creatorBiography;

    private int totalFundingPrice;

    private int fundingCount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Project(ProjectRequestDto projectRequestDto, User user) {
        this.category = projectRequestDto.getCategory();
        this.summary = projectRequestDto.getSummary();
        this.title = projectRequestDto.getTitle();
        this.thumbnails = projectRequestDto.getThumbnails();
        this.goal = projectRequestDto.getGoal();
        this.startDate = projectRequestDto.getStartDate();
        this.endDate = projectRequestDto.getEndDate();
        this.rewards = projectRequestDto.getRewards().stream()
                .map((rewardRequestDto) -> new Reward(rewardRequestDto, this))
                .collect(Collectors.toList());
        this.plan = projectRequestDto.getPlan();
        this.images = projectRequestDto.getImages();
        this.creatorName = projectRequestDto.getCreatorName();
        this.creatorBiography = projectRequestDto.getCreatorBiography();
        this.totalFundingPrice = 0;
        this.fundingCount = 0;
        this.user = user;
    }

    public void addFund(Reward reward) {
        totalFundingPrice += reward.getFundingPrice();
        fundingCount++;
    }

}
