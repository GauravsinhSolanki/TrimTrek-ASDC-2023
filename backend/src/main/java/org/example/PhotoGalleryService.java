package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhotoGalleryService {

    @Autowired
    private PhotoGalleryRepository photoGalleryRepository;

    public PhotoGallery createPhoto(PhotoGallery photo) {
        return photoGalleryRepository.save(photo);
    }

    public List<PhotoGallery> getAllPhotos() {
        return photoGalleryRepository.findAll();
    }

    public PhotoGallery getPhotoById(Long id) {
        return photoGalleryRepository.findById(id).orElse(null);
    }

    public void deletePhoto(Long id) {
        photoGalleryRepository.deleteById(id);
    }
}
