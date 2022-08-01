package com.example.tumblbug.entity;

import com.example.tumblbug.dto.RewardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    private String rewardItem;

    private Integer fundingPrice;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    public Reward(RewardRequestDto rewardRequestDto, Project project) {
        this.rewardItem = rewardRequestDto.getRewardItem();
        this.fundingPrice = rewardRequestDto.getFundingPrice();
        this.project = project;
    }

}
