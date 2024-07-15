package com.richard.SpringBlog.services;

import com.richard.SpringBlog.dtos.RegisterDto;
import com.richard.SpringBlog.entities.User;
import com.richard.SpringBlog.exceptions.UserAlreadyExistsException;
import com.richard.SpringBlog.repositories.UserRepository;
import com.richard.SpringBlog.services.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User registerUser(RegisterDto dto) {
        if (userRepository.findByProfileName(dto.getProfileName()).isPresent()){
            throw new UserAlreadyExistsException("username already exists");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("email already in use");
        }

        User newUser = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .profileName(dto.getProfileName())
                .createdDate(LocalDateTime.now())
                .build();

        userRepository.save(newUser);
        return newUser;
    }
}
