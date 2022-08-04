package com.example.tumblbug.service;

import com.example.tumblbug.dto.MyFundDetailResponseDto;
import com.example.tumblbug.dto.MyFundsResponseDto;
import com.example.tumblbug.entity.Fund;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final FundRepository fundRepository;

    public List<MyFundsResponseDto> getMyFunds(User user) {
        List<Fund> funds = fundRepository.findAllByUser(user);
        return funds.stream()
                .map(MyFundsResponseDto::new)
                .collect(Collectors.toList());
    }

    public MyFundDetailResponseDto getMyFundDetail(Long fundId) {
        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fund " + fundId + " is not found"));
        return new MyFundDetailResponseDto(fund);
    }

}
