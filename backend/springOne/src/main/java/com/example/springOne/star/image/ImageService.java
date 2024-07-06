package com.example.springOne.star.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;


    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] data = file.getBytes();
        Image image = new Image(fileName, contentType, data);
        image = imageRepository.save(image);
        return image.getId();
    }

    public Image getImageById(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image not found with id " + id));
    }
}
