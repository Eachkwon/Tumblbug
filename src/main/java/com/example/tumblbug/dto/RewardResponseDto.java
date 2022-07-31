package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Reward;
import lombok.Getter;

@Getter
public class RewardResponseDto {

    private Long rewardId;

    private String rewardItem;

    private Integer fundingPrice;

    public RewardResponseDto(Reward reward) {
        this.rewardId = reward.getRewardId();
        this.rewardItem = reward.getRewardItem();
        this.fundingPrice = reward.getFundingPrice();
    }

}
