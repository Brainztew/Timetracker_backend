package com.timetracker_backend.timetracker_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController
@RequestMapping("/task")

public class TaskController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from backend!";
    }
}
