package com.unionfly.interview.service;

import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;

public interface UserService {
    String register(UserRegisterRequest userRegisterRequest);

    User getUserById(String userId);
}
