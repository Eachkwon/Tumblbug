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
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String name = requestDto.getName();
        String password = requestDto.getPassword();

        //이메일 중복확인
        userValidator.checkEmail(userRepository.findByEmail(email));
        //비밀번호 중복확인
        userValidator.confirmPassword(password, requestDto.getConfirmPassword());

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(email, name, encodedPassword));
    }

}
