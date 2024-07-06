package com.example.springOne.project;

import com.example.springOne.star.Star;
import com.example.springOne.star.StarLogIn;
import com.example.springOne.star.StarService;
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
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private static final Logger logger = Logger.getLogger(com.example.springOne.project.ProjectController.class.getName());

//
//    @Autowired
//    private HttpSession httpSession;

    private final StarService starService;
    private final ProjectService projectService;

    @Autowired
    public ProjectController(StarService starService, ProjectService projectService) {
        this.projectService = projectService;
        this.starService = starService;
    }

    @GetMapping
    public List<Project> getProjects(){
        return projectService.getProjects();
    }

    @PostMapping("/upload-project")
    public ResponseEntity<?> uploadProject(@RequestBody Project project){
        return projectService.addNewProject(project);
    }
}
