package com.ProjectTrial1.Projectdemo1.photogallery;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imageId;
    private String imageName;
    private String imageDescription;
}
