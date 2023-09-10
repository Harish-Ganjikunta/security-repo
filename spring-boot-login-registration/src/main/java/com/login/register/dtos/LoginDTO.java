package com.login.register.dtos;

import lombok.Data;

@Data
public class LoginDTO {

    private String usernameOrEmail;
    private String password;
}
