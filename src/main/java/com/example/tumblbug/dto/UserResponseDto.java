package com.example.tumblbug.dto;

import com.example.tumblbug.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String email;

    private String name;

    private String password;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
    }

}

