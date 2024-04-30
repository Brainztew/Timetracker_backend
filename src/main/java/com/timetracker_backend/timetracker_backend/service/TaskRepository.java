package com.timetracker_backend.timetracker_backend.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.timetracker_backend.timetracker_backend.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
    
}
