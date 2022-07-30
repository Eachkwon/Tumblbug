package com.example.tumblbug.service;

import com.example.tumblbug.dto.SignupRequestDto;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String name = requestDto.getName();
        String password = requestDto.getPassword();
        String confirmPassword = requestDto.getConfirmPassword();

        //회원가입 빈 값
        userValidator.checkIsEmpty(email, password, name);
        //이메일 중복확인
        Optional<User> foundEmail = userRepository.findByEmail(email);
        userValidator.checkEmail(foundEmail);
        //비밀번호 중복확인
        userValidator.confirmPassword(password, confirmPassword);
        //비밀번호 정규식 유효성검사
        userValidator.checkPassword(password);

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        userRepository.save(new User(email, name, encodedPassword));
    }

    // 이메일 중복확인
    public void emailCheck(String email) {
        Optional<User> foundEmail = userRepository.findByEmail(email);
        userValidator.checkEmail(foundEmail);
    }
}
