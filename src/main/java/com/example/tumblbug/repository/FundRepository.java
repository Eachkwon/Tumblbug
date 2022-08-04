package com.example.tumblbug.repository;

import com.example.tumblbug.entity.Fund;
import com.example.tumblbug.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundRepository extends JpaRepository<Fund, Long> {

    List<Fund> findAllByUser(User user);

}
