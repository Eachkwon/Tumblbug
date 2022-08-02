package com.example.tumblbug.repository;

import com.example.tumblbug.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
