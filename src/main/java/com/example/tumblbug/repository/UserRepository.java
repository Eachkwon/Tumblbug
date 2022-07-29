package com.example.tumblbug.repository;

import com.example.tumblbug.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}