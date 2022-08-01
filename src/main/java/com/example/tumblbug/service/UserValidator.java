package com.example.tumblbug.service;

import com.example.tumblbug.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    //이메일 중복확인
    public void checkEmail(Optional<User> foundEmail) {
        if (foundEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, foundEmail + "is already registered");
        }
    }

    //회원가입 빈 값 금지
    public void checkIsEmpty(String email, String password, String name) {
        if (email.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The email field is required");
        }
        if (password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password field is required");
        }
        if (name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name field is required");
        }
    }

    //비밀번호 중복확인
    public void confirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password confirmation does not match");
        }
    }

    //비밀번호 정규식 유효성검사
    public void checkPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+])[A-Za-z@#$!%\\^*&]{8,16}$";
        if (!Pattern.compile(regex).matcher(password).find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password format is invalid");
        }
    }

}



