package com.example.tumblbug.controller;

import com.example.tumblbug.dto.MyFundDetailResponseDto;
import com.example.tumblbug.dto.MyFundsResponseDto;
import com.example.tumblbug.security.UserDetailsImpl;
import com.example.tumblbug.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/api/my-page")
    public List<MyFundsResponseDto> getMyFunds(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myPageService.getMyFunds(userDetails.getUser());
    }

    @GetMapping("/api/my-page/{fundId}")
    public MyFundDetailResponseDto getMyFundDetail(@PathVariable Long fundId) {
        return myPageService.getMyFundDetail(fundId);
    }

}
