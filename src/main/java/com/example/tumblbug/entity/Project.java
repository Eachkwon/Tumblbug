package com.example.tumblbug.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
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

    @OneToMany(mappedBy = "project")
    private List<Thumbnail> thumbnailUrls;

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

}

