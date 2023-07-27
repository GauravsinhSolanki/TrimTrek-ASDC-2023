package com.ProjectTrial1.Projectdemo1.photogallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoGalleryRepository {
    List<PhotoGallery> findAll();
    Optional<PhotoGallery> findByImageId(String imageId);
    PhotoGallery save(PhotoGallery photoGallery);
}
