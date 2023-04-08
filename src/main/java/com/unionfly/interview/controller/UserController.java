package com.unionfly.interview.controller;

import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;
import com.unionfly.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        String userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> search(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
