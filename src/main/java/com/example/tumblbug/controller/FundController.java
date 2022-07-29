package com.example.tumblbug.controller;

import com.example.tumblbug.dto.FundRequestDto;
import com.example.tumblbug.dto.FundResponseDto;
import com.example.tumblbug.security.UserDetailsImpl;
import com.example.tumblbug.service.FundService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FundController {

    private final FundService fundService;

    // 후원하기
    @PostMapping("/api/funds")
    public FundResponseDto postFund(@RequestBody FundRequestDto fundRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return fundService.fund(fundRequestDto, userDetails.getUser());
    }

}
