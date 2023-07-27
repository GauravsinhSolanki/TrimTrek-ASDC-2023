package com.ProjectTrial1.Projectdemo1.photogallery;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "image_id")
    //image url here
    private String imageId;
    @Column(name = "image_Name")
    private String imageName;
    @Column(name = "image_description")
    private String imageDescription;
}
