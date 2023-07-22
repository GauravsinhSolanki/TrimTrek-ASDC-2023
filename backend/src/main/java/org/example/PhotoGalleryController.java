package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotoGalleryController {

    @Autowired
    private PhotoGalleryService photoGalleryService;

    @PostMapping("/photos")
    public PhotoGallery createPhoto(@RequestBody PhotoGallery photo) {
        return photoGalleryService.createPhoto(photo);
    }

    @GetMapping("/photos")
    public List<PhotoGallery> getAllPhotos() {
        return photoGalleryService.getAllPhotos();
    }

    @GetMapping("/photos/{id}")
    public PhotoGallery getPhotoById(@PathVariable Long id) {
        return photoGalleryService.getPhotoById(id);
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoGalleryService.deletePhoto(id);
    }
}
