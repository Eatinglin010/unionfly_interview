package com.unionfly.interview.model;
import com.mongodb.lang.NonNull;
import com.unionfly.interview.dto.UserRegisterRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String account;
    private Integer status;
    public User() {
    }
    public User(UserRegisterRequest userRegisterRequest) {
        this.setEmail(userRegisterRequest.getEmail());
        this.setPassword(userRegisterRequest.getPassword());
    }
}