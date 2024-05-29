package com.example.springOne.star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StarService {

    private final StarRepository starRepository;

    @Autowired
    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public List<Star> getStars(){
        return starRepository.findAll();
    }

    public void addNewStar(Star star) {
        Optional<Star> starOptional = starRepository
                .findStarByEmail(star.getEmail());
        if(starOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        starRepository.save(star);
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
}
