package com.richard.SpringBlog.services.interfaces;

import com.richard.SpringBlog.dtos.RegisterDto;
import com.richard.SpringBlog.entities.User;

public interface AuthService {
    User registerUser(RegisterDto dto);
}
