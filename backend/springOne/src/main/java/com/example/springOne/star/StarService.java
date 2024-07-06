package com.example.springOne.star;

import com.example.springOne.star.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;


@Service
public class StarService {

    private static final Logger logger = Logger.getLogger(StarService.class.getName());
    Map<String, String> map= new HashMap<>();; //HttpSession 2 StarId

    Map<String, String> map2= new HashMap<>();; //StarId 2 ImageId


    private final StarRepository starRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StarService(StarRepository starRepository, PasswordEncoder passwordEncoder) {
        this.starRepository = starRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Star> getStars(){
        return starRepository.findAll();
    }

    public ResponseEntity<?> addNewStar(Star star) {
        Optional<Star> starOptional1 = starRepository
                .findStarByUsername(star.getUsername());
        if(starOptional1.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username taken");
        }
        Optional<Star> starOptional2 = starRepository
                .findStarByEmail(star.getEmail());
        if(starOptional2.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email taken");
        }

        // Hash the password before saving
            String hashedPassword = passwordEncoder.encode(star.getHashedPassword());
            star.setHashedPassword(hashedPassword);


        starRepository.save(star);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }


    public void deleteStar(String starId) {
        boolean exists = starRepository.existsById(starId);
        if (!exists){
            throw new IllegalStateException("star with id " +starId +" does not exists");
        }
        starRepository.deleteById(starId);
    }


    public void updateStar(String starId,String email){
        Star star = starRepository.findById(starId)
                .orElseThrow(() -> new IllegalStateException(
                        "star with id " +starId +" does not exist"
                ));

        if(email != null && email.length() > 0){
            Optional<Star> starOptional = starRepository.findStarByEmail(email);
            if (starOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            star.setEmail(email);
            starRepository.save(star);  // Explicitly save the updated entity
        }
    }

    public ResponseEntity<?> logIn(StarLogIn star, HttpSession httpSession) {
        logger.log(Level.INFO, "Attempting login for user: {0}", star.getUsername());
        Optional<Star> starOptional = starRepository.findStarByUsername(star.getUsername());
        if (!starOptional.isPresent()){
            logger.log(Level.WARNING, "Username does not exist: {0}", star.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username doesn't exist");
        }
        else {
            if (!passwordEncoder.matches(star.getHashedPassword(), starOptional.get().getHashedPassword())) {
                logger.log(Level.WARNING, "Wrong password for user: {0}", star.getUsername());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("wrong password");
            }
        }
        logger.log(Level.INFO, "User logged in successfully: {0}", star.getUsername());
        map.put(httpSession.getId(), starOptional.get().getId());
        return ResponseEntity.ok(star.getUsername());
    }

    public Star getStarViaSession(HttpSession httpSession) {
        System.out.println("Attempting to find Star via httpSession: " +httpSession.getId());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Http Session: " + entry.getKey());
            System.out.println("Star Id: " + entry.getValue());
        }
        String id = map.get(httpSession.getId());
        System.out.println(id);
        if (id == null) {
            return null;
        }
        else{
            Optional<Star> star = starRepository.findStarById(id);
            return star.orElse(null);
        }
    }

    public ResponseEntity<?> logOut(HttpSession httpSession) {
        map.remove(httpSession);
        return ResponseEntity.ok("Logged out successfully");
    }

    public void updateImageMap(String starId, String imageId) {
        map2.put(starId, imageId);
    }

    public String getImageViaStarId(String id){
        return map2.get(id);
    }

}
