package com.unionfly.interview.service.impl;

import com.unionfly.interview.dao.UserDao;
import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;
import com.unionfly.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }
}
