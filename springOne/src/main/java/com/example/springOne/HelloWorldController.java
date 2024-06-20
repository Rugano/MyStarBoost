package com.example.springOne;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @RequestMapping("/goodbye")
    public String goodbye(){
        return "Goodbye from Spring Boot";
    }
}
