package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Fund;
import lombok.Getter;

@Getter
public class MyFundigHistoryResponeseDto {

    private Long fundId;

    private Long rewardId;

    private String rewardItem;

    private Integer fundingPrice;

    private Long projectId;


    public MyFundigHistoryResponeseDto(Fund fund) {
        this.fundId = fund.getFundId();
        this.rewardId = fund.getReward().getRewardId();
        this.rewardItem = fund.getReward().getRewardItem();
        this.fundingPrice = fund.getReward().getFundingPrice();
        this.projectId = fund.getReward().getProject().getProjectId();
    }
}
