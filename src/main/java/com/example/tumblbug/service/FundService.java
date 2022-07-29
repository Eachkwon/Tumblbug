package com.example.tumblbug.service;

import com.example.tumblbug.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FundService {

    private final FundRepository fundRepository;

}

