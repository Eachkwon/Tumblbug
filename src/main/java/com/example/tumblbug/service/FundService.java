package com.example.tumblbug.service;

import com.example.tumblbug.dto.FundRequestDto;
import com.example.tumblbug.dto.FundResponseDto;
import com.example.tumblbug.entity.Fund;
import com.example.tumblbug.entity.Reward;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.FundRepository;
import com.example.tumblbug.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FundService {

    private final FundRepository fundRepository;

    private final RewardRepository rewardRepository;

    // 후원하기
    @Transactional
    public FundResponseDto fund(FundRequestDto fundRequestDto, User user) {
        Long rewardId = fundRequestDto.getRewardId();
        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reward " + rewardId + " is not found"));
        Fund fund = fundRepository.save(new Fund(user, reward));
        reward.getProject().addFund(reward);
        return new FundResponseDto(fund);
    }

}
