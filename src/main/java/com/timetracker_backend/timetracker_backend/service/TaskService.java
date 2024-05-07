package com.timetracker_backend.timetracker_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.timetracker_backend.timetracker_backend.model.Task;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Task createTask(Task task, String userId) {
        task.setUserId(userId);
        return taskRepository.save(task);
    }

    public Task getTask(String id) {
        Task task = mongoOperations.findById(id, Task.class);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }
        return task;
    }

    public Iterable<Task> getAllTasks() {
        return mongoOperations.findAll(Task.class);
    }

    public Task deleteTask(String id, String userId) {
        Task task = mongoOperations.findById(id, Task.class);
        task.setUserId(userId);
        mongoOperations.remove(task);
        return task;
    }

    public Task updateTask(String id, Task task, String userId) {
        Task existingTask = mongoOperations.findById(id, Task.class);
        task.setUserId(userId);
        existingTask.setName(task.getName());
        mongoOperations.save(existingTask);
        return existingTask;
    }

    public Task startTask(String id, String userId) {
        Task task = mongoOperations.findById(id, Task.class);
        task.setUserId(userId);
        task.setTimerRunning(true);
        task.start();
        mongoOperations.save(task);
        return task;
    }

    public Task endTask(String id) {
        Task task = mongoOperations.findById(id, Task.class);
        task.setTimerRunning(false);
        task.end();
        mongoOperations.save(task);
        return task;
    }

    public long getTaskDuration(String id) {
        Task task = mongoOperations.findById(id, Task.class);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }
        return task.getTotalDuration();
    }

    public List<Task> getUserTasks(String userId) {
        return taskRepository.findByUserId(userId);
    }
    
}
