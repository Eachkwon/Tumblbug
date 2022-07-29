package com.example.tumblbug.controller;

import com.example.tumblbug.service.FundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FundController {

    private final FundService fundService;

}
