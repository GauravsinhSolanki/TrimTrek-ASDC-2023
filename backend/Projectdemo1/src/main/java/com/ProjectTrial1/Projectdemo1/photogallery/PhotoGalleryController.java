package com.ProjectTrial1.Projectdemo1.photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photo-gallery")
public class PhotoGalleryController {
    @Autowired
    PhotoGalleryService photoGalleryService;




    @GetMapping
    public ResponseEntity<List<PhotoGallery>> getAllPhotoGallery() {
        List<PhotoGallery> photoGalleryList = photoGalleryService.getAllPhotoGallery();
        return new ResponseEntity<>(photoGalleryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoGallery> getPhotoGalleryById(@PathVariable int id) {
        Optional<PhotoGallery> photoGallery = photoGalleryService.getPhotoGalleryById(id);
        return photoGallery.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PhotoGallery> createPhotoGallery(@RequestBody PhotoGallery photoGallery) {
        PhotoGallery savedPhotoGallery = photoGalleryService.savePhotoGallery(photoGallery);
        return new ResponseEntity<>(savedPhotoGallery, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoGallery> updatePhotoGallery(@PathVariable int id, @RequestBody PhotoGallery photoGallery) {
        Optional<PhotoGallery> existingPhotoGallery = photoGalleryService.getPhotoGalleryById(id);
        if (existingPhotoGallery.isPresent()) {
            photoGallery.setId(id);
            PhotoGallery updatedPhotoGallery = photoGalleryService.savePhotoGallery(photoGallery);
            return new ResponseEntity<>(updatedPhotoGallery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoGallery(@PathVariable int id) {
        Optional<PhotoGallery> existingPhotoGallery = photoGalleryService.getPhotoGalleryById(id);
        if (existingPhotoGallery.isPresent()) {
            photoGalleryService.deletePhotoGallery(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
