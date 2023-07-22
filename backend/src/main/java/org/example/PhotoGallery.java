package org.example;

import javax.persistence.*;

@Entity
public class PhotoGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageName;
    private String imageURL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
