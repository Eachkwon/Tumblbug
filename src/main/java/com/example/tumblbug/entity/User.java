package com.example.tumblbug.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // 로그인용 이메일
    @Column(nullable = false, unique = true)
    private String email;

    //이름 (e.g. 홍길동)
    @Column(nullable = false)
    private String name;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

}
