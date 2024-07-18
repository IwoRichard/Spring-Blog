package com.richard.SpringBlog.dtos;

import com.richard.SpringBlog.entities.User;
import lombok.Data;

@Data
public class LoginResponse {

    private String accessToken;
    private User user;
}
