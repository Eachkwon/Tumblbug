package com.example.tumblbug.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
public class SignupRequestDto {

    @Email
    private String email;

    @NotBlank
    private String name;

    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+])[A-Za-z@#$!%\\^*&]{8,16}$")
    private String password;

    @NotBlank
    private String confirmPassword;

}

