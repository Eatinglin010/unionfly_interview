package com.unionfly.interview.dao;

import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;

public interface UserDao {
    String createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(String userId);
}
