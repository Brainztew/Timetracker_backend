package com.timetracker_backend.timetracker_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker_backend.timetracker_backend.model.Task;
import com.timetracker_backend.timetracker_backend.service.TaskService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;
    
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id) {
    return taskService.getTask(id);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUser(@PathVariable String userId) {
        return taskService.getUserTasks(userId);
    }

    @GetMapping("/duration/{id}")
    public long getTaskDuration(@PathVariable String id) {
    return taskService.getTaskDuration(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task, @RequestParam String userId) {
        return taskService.createTask(task, userId);
    }

    @GetMapping("/all")
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable String id, @RequestParam String userId) {
        taskService.deleteTask(id, userId );
    }

    @PatchMapping("/update/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task task, @RequestParam String userId) {
        return taskService.updateTask(id, task, userId);
    }

    @PostMapping("/start/{id}")
    public Task startTask(@PathVariable String id, @RequestParam String userId) {
        Task task = taskService.getTask(id);
        if (task.isTimerRunning()) {
            throw new IllegalArgumentException("Task is already running");
        }
        return taskService.startTask(id, userId);
    }

    @PostMapping("/end/{id}")
    public Task endTask(@PathVariable String id, @RequestParam String userId) {
        Task task = taskService.getTask(id);
        if (!task.isTimerRunning()) {
            throw new IllegalArgumentException("Task is not running");
        }
        return taskService.endTask(id);
    }


}
