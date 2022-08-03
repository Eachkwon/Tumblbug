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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, foundEmail + "is already registered");
        }
    }

    //비밀번호 중복확인
    public void confirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password confirmation does not match");
        }
    }

}



