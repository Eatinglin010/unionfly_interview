package com.unionfly.interview.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import com.unionfly.interview.dto.UserRegisterRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    @Email
    private String email;
    @JsonIgnore
    private String password;
    private String account;
    private String jwt;
    private Date updateTime;
    public User() {
    }
    public User(UserRegisterRequest userRegisterRequest) {
        this.setEmail(userRegisterRequest.getEmail());
        this.setPassword(userRegisterRequest.getPassword());
    }

}