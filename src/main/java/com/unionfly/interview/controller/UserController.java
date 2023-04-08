package com.unionfly.interview.controller;

import com.unionfly.interview.dto.UserRegisterRequest;
import com.unionfly.interview.dto.UserVerifyRequest;
import com.unionfly.interview.model.User;
import com.unionfly.interview.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        String userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    @GetMapping("/user")
    public ResponseEntity<String> searchByJwt(HttpServletRequest req){
        String authorization =  req.getHeader(AUTHORIZATION);
        User user = userService.getAcountByJwt(authorization);
        return  ResponseEntity.status(HttpStatus.OK).body(user.getAccount());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> search(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> update(@PathVariable String userId ,@RequestBody @Valid User user){
        User originalUser = userService.getUserById(userId);
        if(originalUser==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User不存在");
        }
        userService.updateUser(userId,user);
        return  ResponseEntity.status(HttpStatus.OK).body("成功更新User:"+userId);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userId,HttpServletRequest req){
        String authorization =  req.getHeader(AUTHORIZATION);
        User user = userService.getAcountByJwt(authorization);
        if(user.getId().equals(userId)){
            String resault = userService.deleteById(userId);
            return  ResponseEntity.status(HttpStatus.OK).body(resault);
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您沒有此操作權限");
        }

    }


    @PostMapping("/user/login")
    public ResponseEntity<String> userLogin(@RequestBody UserVerifyRequest userVerifyRequest){
            String result=userService.verifyUser(userVerifyRequest.getEmail(), userVerifyRequest.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping("/user/logout")
    public ResponseEntity<String> userLogout(HttpServletRequest req){
        String authorization =  req.getHeader(AUTHORIZATION);
        User user = userService.getAcountByJwt(authorization);
        if(user!=null) {
            User logoutUser = new User();
            logoutUser.setJwt("");
            userService.updateUser(user.getId(),logoutUser);
            return ResponseEntity.status(HttpStatus.OK).body("登出成功");
        }
        return ResponseEntity.status(HttpStatus.OK).body("您尚未登入");
    }
}
