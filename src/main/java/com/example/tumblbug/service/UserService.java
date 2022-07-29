package com.example.tumblbug.service;

import com.example.tumblbug.dto.UserRequestDto;
import com.example.tumblbug.dto.UserResponseDto;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        // TODO: 회원가입 유효성 검사
        //  - Validator 또는 Annotation
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = userRepository.save(new User(userRequestDto.getEmail(), userRequestDto.getName(), encodedPassword));
        return new UserResponseDto(user);
    }

}
