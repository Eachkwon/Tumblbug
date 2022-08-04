package com.example.tumblbug.controller;

import com.example.tumblbug.dto.SignupRequestDto;
import com.example.tumblbug.service.KakaoUserService;
import com.example.tumblbug.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    // 회원가입 + 카카오 추가
    @PostMapping("/api/signup")
    public void signup(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    //카카오 로그인
    @GetMapping("/kakao/callback")
    public void loginByKakao(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.loginByKakao(code);
    }

}

