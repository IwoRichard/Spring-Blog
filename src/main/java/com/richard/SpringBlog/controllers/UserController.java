package com.richard.SpringBlog.controllers;

import com.richard.SpringBlog.dtos.UpdateBioDto;
import com.richard.SpringBlog.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/updateBio")
    public ResponseEntity<String> updateBio(@PathVariable Long userId, @RequestBody UpdateBioDto dto){
        userService.updateProfileBio(userId, dto);
        return new ResponseEntity<>(dto.getBio(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{userId}/updateProfilePicture")
    public ResponseEntity<String> updateProfilePicture(@PathVariable Long userId, @RequestParam(name = "file")MultipartFile file){
        String pictureUrl = userService.updateProfilePicture(userId, file);
        return new ResponseEntity<>(pictureUrl, HttpStatus.ACCEPTED);
    }

}
