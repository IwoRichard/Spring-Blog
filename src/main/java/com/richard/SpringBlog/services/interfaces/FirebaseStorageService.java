package com.richard.SpringBlog.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    String uploadFile(MultipartFile file, String folder, String username);
}
