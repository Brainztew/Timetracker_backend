package com.timetracker_backend.timetracker_backend.service;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.timetracker_backend.timetracker_backend.model.User;

public interface UserRepository extends MongoRepository<User, String>{
    User findByUsername(String username);


    /* User findByUsernameAndPassword(String username, String password); */
}
