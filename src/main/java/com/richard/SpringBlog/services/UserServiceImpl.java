package com.richard.SpringBlog.services;

import com.richard.SpringBlog.dtos.UpdateBioDto;
import com.richard.SpringBlog.entities.User;
import com.richard.SpringBlog.repositories.UserRepository;
import com.richard.SpringBlog.services.interfaces.FirebaseStorageService;
import com.richard.SpringBlog.services.interfaces.UserService;
import com.richard.SpringBlog.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    @Autowired
    private ServiceHelper serviceHelper;

    @Override
    public String updateProfilePicture(Long userId, MultipartFile file) {
        User user = serviceHelper.findUserById(userId);
        String fileUrl = firebaseStorageService.uploadFile(file, "ProfilePictures", user.getProfileName());

        user.setProfilePicUrl(fileUrl);
        userRepository.save(user);

        return fileUrl;
    }

    @Override
    public String updateProfileBio(Long userId, UpdateBioDto dto) {
        User user = serviceHelper.findUserById(userId);

        user.setProfileBio(dto.getBio());
        userRepository.save(user);

        return dto.getBio();
    }
}
