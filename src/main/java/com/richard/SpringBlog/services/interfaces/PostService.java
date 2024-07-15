package com.richard.SpringBlog.services.interfaces;

import com.richard.SpringBlog.dtos.PostDto;
import com.richard.SpringBlog.entities.Post;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    Post createPost(Long userId, PostDto postDto, MultipartFile file);

    Post editPost(Long userId, Long postId, PostDto postDto);

    String deletePost(Long userId, Long postId);
}
