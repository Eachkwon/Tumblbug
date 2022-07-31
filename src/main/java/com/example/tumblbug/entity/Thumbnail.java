package com.example.tumblbug.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "thumbnail")
public class Thumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thumbnailId;

    private String url;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

}
