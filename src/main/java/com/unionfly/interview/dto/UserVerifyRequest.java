package com.unionfly.interview.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserVerifyRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
