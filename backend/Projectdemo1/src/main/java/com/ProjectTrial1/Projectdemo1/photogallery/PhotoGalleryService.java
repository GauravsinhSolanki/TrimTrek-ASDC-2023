package com.ProjectTrial1.Projectdemo1.photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoGalleryService {
    @Autowired
    PhotoGalleryRepository photoGalleryRepository;

    public List<PhotoGallery> getAllPhotoGallery() {
        return photoGalleryRepository.findAll();
    }

    public Optional<PhotoGallery> getPhotoGalleryByEmailId(String imageId) {
        return photoGalleryRepository.findByImageId(imageId);
    }

    public PhotoGallery savePhotoGallery(PhotoGallery photoGallery) {
        return photoGalleryRepository.save(photoGallery);
    }


}
