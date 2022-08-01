package com.example.tumblbug.service;

import com.example.tumblbug.dto.FundRequestDto;
import com.example.tumblbug.dto.FundResponseDto;
import com.example.tumblbug.entity.Fund;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.Reward;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.FundRepository;
import com.example.tumblbug.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;

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
        Project project = reward.getProject();
        if (LocalDate.now().isBefore(project.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아직 펀딩을 시작하지 않았습니다");
        }
        if (LocalDate.now().isAfter(project.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 펀딩이 종료되었습니다");
        }
        Fund fund = fundRepository.save(new Fund(user, reward));
        project.addFund(reward);
        return new FundResponseDto(fund);
    }

}
