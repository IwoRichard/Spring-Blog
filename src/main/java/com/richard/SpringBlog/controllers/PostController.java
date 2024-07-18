package com.richard.SpringBlog.controllers;

import com.richard.SpringBlog.dtos.PaginatedPostResponse;
import com.richard.SpringBlog.dtos.PostDto;
import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<PaginatedPostResponse> getPostsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0", value = "pageNo") int pageNo,
            @RequestParam(defaultValue = "10", value = "pageSize") int pageSize) {

        Page<Post> postPage = postService.findAllPostsByUser(userId, pageNo, pageSize);
        PaginatedPostResponse paginatedPostResponse = PaginatedPostResponse.builder()
                .pageNumber(postPage.getNumber())
                .pageSize(postPage.getSize())
                .totalElements(postPage.getTotalElements())
                .totalPages(postPage.getTotalPages())
                .content(postPage.getContent())
                .build();

        return new ResponseEntity<>(paginatedPostResponse, HttpStatus.OK);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Post> createPost(
            @PathVariable Long userId,
            @RequestParam(name = "file")MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content){
        PostDto postDto = new PostDto();
        postDto.setPostTitle(title);
        postDto.setPostContent(content);
        Post newPost = postService.createPost(userId, postDto, file);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<Post> editPost(
            @PathVariable Long userId,
            @RequestParam(required = true, name = "postId") Long postId,
            @RequestBody PostDto postDto){
        Post editedPost = postService.editPost(userId, postId, postDto);
        return new ResponseEntity<>(editedPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long userId, @RequestParam(required = true, name = "postId") Long postId){
        return new ResponseEntity<>(postService.deletePost(userId, postId), HttpStatus.OK);
    }
}
