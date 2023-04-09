package com.unionfly.interview.service.impl;

import com.unionfly.interview.dao.UserDao;
import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.model.User;
import com.unionfly.interview.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public String deleteById(String userId) {
        return userDao.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getAcountByJwt(String jwt) {
        return userDao.getUserByJwt(jwt);
    }

    @Override
    public void updateUser(String userId, User user) {
        userDao.updateUser(userId,user);
    }

    public String verifyUser(String email , String userPasswd){
        /*
         0000 login success
         0001 wrong email
         0002 wrong passwd
         9999 unknown error
         */
        User user=userDao.getUserByEmail(email);
        String result="9999";
        if(user !=null ){
            if(user.getPassword().equals(userPasswd)){
                Date expireDate =new Date(System.currentTimeMillis()+ 30 * 60 * 1000);
                    String jwtToken = Jwts.builder()
                            .setSubject(email)
                            .setExpiration(expireDate)
                                .signWith(Keys.hmacShaKeyFor("unionflyunionflyunionflyunionflyunionflyunionflyunionflyunionflyunionfly".getBytes()))
                        .compact();
                result=jwtToken;
                user.setJwt(jwtToken);
                userDao.updateUser(user.getId(),user);
            }else{
                result="0002";
            }
        }else{
            result="0001";
        }
        return result;
    }

    @Override
    public void isVerify(String jwt) {
        User user = userDao.getUserByJwt(jwt);
        if(userDao.getUserByJwt(jwt)==null){
            throw new MalformedJwtException("身分驗證錯誤");
        }
        System.out.println("通過驗證");
    }
}
