package com.unionfly.interview.dao;

import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;

public interface UserDao {
    String createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(String userId);

    User getUserByEmail(String email);

    User getUserByJwt(String jwt);

    String deleteById(String userId);

    void updateUser(String userId, User user);

}
