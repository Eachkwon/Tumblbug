package com.example.tumblbug.repository;

//import com.amazonaws.services.ec2.model.Image;

import com.example.tumblbug.entity.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
    Thumbnail findImageByImgUrl(String imagepath);
}
