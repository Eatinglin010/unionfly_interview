package com.unionfly.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/users")
    public String insert(@RequestBody User user){
        mongoTemplate.insert(user);
        return  "執行 INSERT sql";
    }
    @DeleteMapping("/students/{userId}")
    public String delete(@PathVariable Integer userId){
        return  "執行 DELETE sql";
    }
}
