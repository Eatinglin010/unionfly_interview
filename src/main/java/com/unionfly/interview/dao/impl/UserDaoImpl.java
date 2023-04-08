package com.unionfly.interview.dao.impl;

import com.unionfly.interview.dao.UserDao;
import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createUser(UserRegisterRequest userRegisterRequest) {
        User user = new User(userRegisterRequest);

        return mongoTemplate.insert(user).getId();
    }

    @Override
    public User getUserById(String userId) {
        return (User)mongoTemplate.findById(userId,User.class);
    }
}
