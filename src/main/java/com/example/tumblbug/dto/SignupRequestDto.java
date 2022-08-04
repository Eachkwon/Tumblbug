package com.example.tumblbug.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class SignupRequestDto {

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, max= 20, message = "이름은 2~20자 내로 입력해주세요.")
    private String name;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+])[A-Za-z@#$!%\\^*&]{8,16}$", message = "비밀번호는 8~16자의 영대소문자와 특수기호로 이루어져야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String confirmPassword;

}

