package com.example.springOne.project;


import com.example.springOne.star.Star;
import com.example.springOne.star.StarLogIn;
import com.example.springOne.star.StarRepository;
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
public class ProjectService {

    private static final Logger logger = Logger.getLogger(com.example.springOne.project.ProjectService.class.getName());

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public ResponseEntity<?> addNewProject(Project project) {
        projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body("Project uploaded successfully");
    }
}
