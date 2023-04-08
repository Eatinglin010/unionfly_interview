package com.unionfly.interview;
import com.mongodb.lang.NonNull;
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
    @NonNull
    private String email;
    @Indexed(unique = true)
    @NonNull
    private String userAccount;
    @NonNull
    private String userPassword;
    @NonNull
    private Integer depositAccount;
    @NonNull
    private Integer status;
}