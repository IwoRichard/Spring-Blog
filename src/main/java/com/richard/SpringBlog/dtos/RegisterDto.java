package com.richard.SpringBlog.dtos;

import lombok.Data;

@Data
public class RegisterDto {

    private String email;
    private String profileName;
    private String password;
}
