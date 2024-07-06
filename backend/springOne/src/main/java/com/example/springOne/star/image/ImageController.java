package com.example.springOne.star.image;

import com.example.springOne.star.StarController;
import com.example.springOne.star.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping(path = "api/v1/star/image")
public class ImageController {

    private static final Logger logger = Logger.getLogger(ImageController.class.getName());


    @Autowired
    private ImageService imageService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private StarService starService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            logger.log(Level.INFO, "User attempting to upload file");
            String imageId = imageService.uploadImage(file);
            String starId = starService.getStarViaSession(httpSession).getId();
            //Need to delete the last profile picture image
            starService.updateImageMap(starId, imageId);
            logger.log(Level.INFO, "everyting ok from upload");
            return ResponseEntity.ok(imageId);
        } catch (Exception e) {
            logger.log(Level.INFO, "Big Balagan1");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Image> getImageById() {
        try {
//            Image image = imageService.getImageById("66840ef493dbd3166dd74cfb");
            logger.log(Level.INFO, "User attempting to get a photo");
            String starId = starService.getStarViaSession(httpSession).getId();
            logger.log(Level.INFO, "User 1");
            String imageId = starService.getImageViaStarId(starId);
            logger.log(Level.INFO, "User attempting to initiate a photo");
            Image image = imageService.getImageById(imageId);
            if (image != null) {
                logger.log(Level.INFO, "Everything ok from get");
                return ResponseEntity.ok(image);
            } else {
                logger.log(Level.INFO, "Big Balagan2");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Big Balagan3");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
