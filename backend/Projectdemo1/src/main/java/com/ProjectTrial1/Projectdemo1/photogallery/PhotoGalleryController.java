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

    @GetMapping("/{imageId}")
    public ResponseEntity<PhotoGallery> getPhotoGalleryByImageId(@PathVariable String imageId) {
        Optional<PhotoGallery> photoGallery = photoGalleryService.getPhotoGalleryByEmailId(imageId);
        return photoGallery.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PhotoGallery> createPhotoGallery(@RequestBody PhotoGallery photoGallery) {
        PhotoGallery savedPhotoGallery = photoGalleryService.savePhotoGallery(photoGallery);
        return new ResponseEntity<>(savedPhotoGallery, HttpStatus.CREATED);
    }



}
