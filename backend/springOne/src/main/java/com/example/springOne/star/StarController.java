package com.example.springOne.star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;


//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping(path = "api/v1/star")
public class StarController {
    private static final Logger logger = Logger.getLogger(StarController.class.getName());


    @Autowired
    private HttpSession httpSession;
    private final StarService starService;

    @Autowired
    public StarController(StarService starService){
        this.starService = starService;
    }

    @GetMapping
    public List<Star> getStars(){
        return starService.getStars();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewStar(@RequestBody Star star){
        return starService.addNewStar(star);
    }

    @PostMapping("/log-in")
    public ResponseEntity<?>login(@RequestBody StarLogIn star){
        logger.log(Level.INFO, "User attempting to log in: {0}", star.getUsername());
        return starService.logIn(star, httpSession);
        //map insertion using httpSession!

    }

    @DeleteMapping("/log-out")
    public ResponseEntity<?> logout(){
        logger.log(Level.INFO, "User attempting to log out: {0}", httpSession.getId());
        return starService.logOut(httpSession);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> Profile(){
        Star star = starService.getStarViaSession(httpSession);
        if (star == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There has been a problem");
        else
            return ResponseEntity.ok(star);
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
