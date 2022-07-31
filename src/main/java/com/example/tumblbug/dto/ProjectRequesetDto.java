package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Image;
import com.example.tumblbug.entity.Reward;
import com.example.tumblbug.entity.Thumbnail;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProjectRequesetDto {

    private String category;

    private String summary;

    private String title;

    private List<Thumbnail> thumbnailUrls;

    private Integer goal;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<Reward> rewards;

    private String plan;

    private List<Image> images;

    private String creatorName;

    private String creatorBiography;

    private int totalFundingPrice;

    private int fundingCount;

}
