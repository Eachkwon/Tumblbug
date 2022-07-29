package com.example.tumblbug.service;

import com.example.tumblbug.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RewardService {

    private final RewardRepository rewardRepository;

}

