package com.example.tumblbug.dto;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class SignupRequestDto {

    @Email
    private String email;

    private String name;

    private String password;

    private String confirmPassword;

}

