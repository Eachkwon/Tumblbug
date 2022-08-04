package com.example.tumblbug.service;

import com.example.tumblbug.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class UserValidator {

    //이메일 중복확인
    public void checkEmail(Optional<User> foundEmail) {
        if (foundEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.");
        }
    }

    //비밀번호 중복확인
    public void confirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }
    }

}



