package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Fund;
import lombok.Getter;

@Getter
public class MyFundsResponseDto {

    Long fundId;

    String projectTitle;

    String projectThumbnail;

    String rewardItem;

    Integer rewardFundingPrice;

    public MyFundsResponseDto(Fund fund) {
        this.fundId = fund.getFundId();
        this.projectTitle = fund.getReward().getProject().getTitle();
        this.projectThumbnail = fund.getReward().getProject().getThumbnails().get(0).getUrl();
        this.rewardItem = fund.getReward().getRewardItem();
        this.rewardFundingPrice = fund.getReward().getFundingPrice();
    }

}
