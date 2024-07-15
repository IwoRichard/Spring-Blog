package com.richard.SpringBlog.services.interfaces;

import com.richard.SpringBlog.dtos.UpdateBioDto;
import com.richard.SpringBlog.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    String updateProfilePicture(Long userId, MultipartFile file);

    String updateProfileBio(Long userId, UpdateBioDto dto);
}
