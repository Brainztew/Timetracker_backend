package com.timetracker_backend.timetracker_backend.service;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.timetracker_backend.timetracker_backend.model.Task;

@Service
public class TaskService {

    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Task createTask(Task task) {
        mongoOperations.save(task);
        return task;
    }
    
}
