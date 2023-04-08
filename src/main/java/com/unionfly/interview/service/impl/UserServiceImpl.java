package com.unionfly.interview.service.impl;

import com.unionfly.interview.dao.UserDao;
import com.unionfly.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
}
