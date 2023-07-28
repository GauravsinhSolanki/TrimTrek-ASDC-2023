package com.ProjectTrial1.Projectdemo1.photogallery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class PhotoGalleryServiceTest {
    @Mock
    private PhotoGalleryRepository photoGalleryRepository;

    @InjectMocks
    private PhotoGalleryService photoGalleryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPhotoGallery() {
        PhotoGallery photo1 = new PhotoGallery(1, "1", "name1", "desc1");
        PhotoGallery photo2 = new PhotoGallery(2, "2", "name2", "desc2");
        List<PhotoGallery> expectedPhotos = Arrays.asList(photo1, photo2);

        when(photoGalleryRepository.findAll()).thenReturn(expectedPhotos);

        List<PhotoGallery> result = photoGalleryService.getAllPhotoGallery();

        assertEquals(expectedPhotos, result);
    }

    @Test
    void getPhotoGalleryByEmailId() {
        String imageId = "1";
        PhotoGallery expectedPhoto = new PhotoGallery(1, "1", "name1", "desc1");

        when(photoGalleryRepository.findByImageId(imageId)).thenReturn(Optional.of(expectedPhoto));

        Optional<PhotoGallery> result = photoGalleryService.getPhotoGalleryByEmailId(imageId);

        assertTrue(result.isPresent());
        assertEquals(expectedPhoto, result.get());
    }

    @Test
    void savePhotoGallery() {
        PhotoGallery photo = new PhotoGallery(1, "1", "name1", "desc1");

        when(photoGalleryRepository.save(photo)).thenReturn(photo);

        PhotoGallery result = photoGalleryService.savePhotoGallery(photo);

        assertEquals(photo, result);
    }
}