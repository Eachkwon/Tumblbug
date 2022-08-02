package com.example.tumblbug.service;

import com.example.tumblbug.dto.SignupRequestDto;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String name = requestDto.getName();

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        userRepository.save(new User(email, name, encodedPassword));
    }

}
