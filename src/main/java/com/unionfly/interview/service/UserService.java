package com.unionfly.interview.service;

import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;

public interface UserService {
    String register(UserRegisterRequest userRegisterRequest);

    String deleteById(String userId);

    User getUserById(String userId);

    User getAcountByJwt(String jwt);

    void updateUser(String userId, User user);

    String verifyUser(String email, String userPassword);

    void isVerify(String jwt);
}
