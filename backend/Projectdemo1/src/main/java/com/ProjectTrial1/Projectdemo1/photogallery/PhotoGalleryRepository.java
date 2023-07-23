package com.ProjectTrial1.Projectdemo1.photogallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, Integer> {
}
