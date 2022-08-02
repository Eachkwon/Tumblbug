package com.example.tumblbug.service;

import com.example.tumblbug.dto.FundResponseDto;
import com.example.tumblbug.dto.MyFundigHistoryResponeseDto;
import com.example.tumblbug.entity.Fund;
import com.example.tumblbug.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MyPageService {
    private final FundRepository fundRepository;

    public List<FundResponseDto> getfundList(Long userId) {
        List<Fund> funds = fundRepository.findAllByUser(userId);
        return funds.stream()
                .map(FundResponseDto::new)
                .collect(Collectors.toList());
    }


    public MyFundigHistoryResponeseDto getMyFundingHistory(Long userId, Long fundId) {
        Fund fund = fundRepository.findFundByUserAndFundId(userId, fundId);
        MyFundigHistoryResponeseDto myFundigHistoryResponeseDto = new MyFundigHistoryResponeseDto(fund);
        return myFundigHistoryResponeseDto;
    }
}
