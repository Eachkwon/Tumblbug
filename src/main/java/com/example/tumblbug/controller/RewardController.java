package com.example.tumblbug.controller;

import com.example.tumblbug.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RewardController {

    private final RewardService rewardService;

}

