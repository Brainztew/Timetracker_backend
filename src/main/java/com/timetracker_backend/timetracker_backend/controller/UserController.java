package com.timetracker_backend.timetracker_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker_backend.timetracker_backend.model.User;
import com.timetracker_backend.timetracker_backend.model.UserTotal;
import com.timetracker_backend.timetracker_backend.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/user")
public class UserController {

    private UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }
    
    @GetMapping("/all")
    public List<UserTotal> getAllUsers(@RequestParam String userId) {
        return userservice.getAllUsers(userId);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello From Backend!";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = userservice.login(credentials.get("username"), credentials.get("password"));
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userservice.createUser(user);
    }


    
}
