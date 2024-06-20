package com.example.springOne.star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/star")
public class StarController {

    private final StarService starService;

    @Autowired
    public StarController(StarService starService){
        this.starService = starService;
    }

    @GetMapping
    public List<Star> getStars(){
        return starService.getStars();
    }

    @PostMapping
    public void registerNewStar(@RequestBody Star star){
        starService.addNewStar(star);
    }

    @DeleteMapping(path ="{starId}")
    public void deleteStar (@PathVariable("starId") String starId) {
        starService.deleteStar(starId);
    }

    @PutMapping(path = "{starId}")
    public void updateStar(
            @PathVariable("starId") String starId,
            @RequestParam(required = false) String email){
        starService.updateStar(starId, email);
    }
}
