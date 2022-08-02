package com.example.tumblbug.controller;

import com.example.tumblbug.dto.SignupRequestDto;
import com.example.tumblbug.repository.UserRepository;
import com.example.tumblbug.service.UserService;
import com.example.tumblbug.service.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    // 회원가입
    @PostMapping("/api/signup")
    public void signup(@RequestBody @Valid SignupRequestDto requestDto) {
        //이메일 중복확인
        userValidator.checkEmail(userRepository.findByEmail(requestDto.getEmail()));
        //비밀번호 중복확인
        userValidator.confirmPassword(requestDto.getPassword(), requestDto.getConfirmPassword());

        userService.signup(requestDto);
    }

}

