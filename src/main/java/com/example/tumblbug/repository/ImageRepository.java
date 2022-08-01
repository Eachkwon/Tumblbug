package com.example.tumblbug.repository;

//import com.amazonaws.services.ec2.model.Image;

import com.example.tumblbug.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByImgUrl(String imagepath);
}
