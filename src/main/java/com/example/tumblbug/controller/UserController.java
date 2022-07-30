package com.example.tumblbug.controller;

import com.example.tumblbug.dto.SignupRequestDto;
import com.example.tumblbug.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/api/signup")
    public void signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    // 이메일 중복확인
    @PostMapping("/api/emailCheck")
    public void emailCheck(@RequestBody String email) {
        userService.emailCheck(email);
    }

}

