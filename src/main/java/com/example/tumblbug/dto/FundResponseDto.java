package com.example.tumblbug.dto;

import com.example.tumblbug.entity.Fund;
import lombok.Getter;

@Getter
public class FundResponseDto {

    Long fundId;

    Long userId;

    Long projectId;

    Long rewardId;

    public FundResponseDto(Fund fund) {
        this.fundId = fund.getFundId();
        this.userId = fund.getUser().getUserId();
        this.projectId = fund.getReward().getProject().getProjectId();
        this.rewardId = fund.getReward().getRewardId();
    }

}
