package com.richard.SpringBlog.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import com.richard.SpringBlog.services.interfaces.FirebaseStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    public String uploadFile(MultipartFile file, String folder, String username) {
        String fileName = folder + "/" + username + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        try {
            BlobInfo blobInfo = StorageClient.getInstance().bucket().create(fileName, file.getInputStream(), file.getContentType());
            String token = UUID.randomUUID().toString();

            Blob blob = StorageClient.getInstance().bucket().get(fileName);
            Map<String, String> newMetadata = new HashMap<>();
            newMetadata.put("firebaseStorageDownloadTokens", token);
            blob.toBuilder().setMetadata(newMetadata).build().update();

            return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media&token=%s",
                    "simpleblogstorage.appspot.com",
                    fileName.replace("/", "%2F"),
                    token);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

}
