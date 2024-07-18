package com.richard.SpringBlog.services;

import com.richard.SpringBlog.dtos.LoginDto;
import com.richard.SpringBlog.dtos.RegisterDto;
import com.richard.SpringBlog.entities.User;
import com.richard.SpringBlog.exceptions.ResourceNotFoundException;
import com.richard.SpringBlog.exceptions.UserAlreadyExistsException;
import com.richard.SpringBlog.repositories.UserRepository;
import com.richard.SpringBlog.services.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  PasswordEncoder passwordEncoder;


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
                .password(passwordEncoder.encode(dto.getPassword()))
                .profileName(dto.getProfileName())
                .createdDate(LocalDateTime.now())
                .build();

        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        if (!user.isEnabled()){
            throw new ResourceNotFoundException("Account is not activated");
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed");
        }
        return user;
    }
}
