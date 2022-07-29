package com.example.tumblbug.controller;

import com.example.tumblbug.dto.UserRequestDto;
import com.example.tumblbug.dto.UserResponseDto;
import com.example.tumblbug.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public UserResponseDto postUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

}

