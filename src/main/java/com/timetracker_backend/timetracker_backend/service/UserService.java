package com.timetracker_backend.timetracker_backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.timetracker_backend.timetracker_backend.model.Task;
import com.timetracker_backend.timetracker_backend.model.User;
import com.timetracker_backend.timetracker_backend.model.UserTotal;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public List<UserTotal> getAllUsers(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.isAdmin()) {
            throw new RuntimeException("User is not an admin");
        }

        List<User> users = userRepository.findAll();
        return users.stream()
        .map(User -> {
            long totalDuration = taskRepository.findByUserId(User.getId()).stream()
            .mapToLong(Task::getTotalDuration)
            .sum();
            return new UserTotal(User, totalDuration);
        })
        .collect(Collectors.toList());
    }

/*     public ResponseEntity<String> login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password!");
        }
    
        return ResponseEntity.ok(user.getId());
    } */

 public Map<String, Object> login(String username, String password) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
        throw new RuntimeException("Invalid username or password");
    }
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid username or password");
    }
    Map<String, Object> response = new HashMap<>();
    response.put("userId", user.getId());
    response.put("isAdmin", user.isAdmin());
    return response;
}
}

