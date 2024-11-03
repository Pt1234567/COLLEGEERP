package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.entities.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {



    @GetMapping("/student")
    public ResponseEntity<String> studentPage(){
        return ResponseEntity.ok("{\"message\": \"Welcome to the Student Page\"}");
    }

    @GetMapping("/teacher")
    public ResponseEntity<String> teacherPage(){
        return ResponseEntity.ok("{\"message\": \"Welcome to the Teacher Page\"}");
    }




}
