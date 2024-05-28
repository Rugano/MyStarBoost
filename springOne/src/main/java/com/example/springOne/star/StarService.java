package com.example.springOne.star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Star addNewStar(Star star) {
        System.out.println("Adding new Star: " + star);
        return star;
    }

}
