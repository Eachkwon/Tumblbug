package com.example.tumblbug.controller;

import com.example.tumblbug.dto.FundResponseDto;
import com.example.tumblbug.dto.MyFundigHistoryResponeseDto;
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

    @GetMapping("api/mypage")
    public List<FundResponseDto> getFundList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getUserId();
        return myPageService.getfundList(userId);
    }

    @GetMapping("api/mypage/{fundId}")
    public MyFundigHistoryResponeseDto getMyFundingHistory(@PathVariable Long fundId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getUserId();
        return myPageService.getMyFundingHistory(fundId, userId);
    }


}
