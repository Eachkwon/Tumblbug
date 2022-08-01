package com.example.tumblbug.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String imgFileName;


    private String imgUrl;

//    @ManyToOne
//    @JoinColumn(name = "projectId")
//    private Project project;
//

    public Image(String fileName, String imgUrl) {
        this.imgFileName = fileName;
        this.imgUrl = imgUrl;
    }
}
