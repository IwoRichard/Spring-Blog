package com.richard.SpringBlog.controllers;

import com.richard.SpringBlog.dtos.PostDto;
import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.services.ServiceHelper;
import com.richard.SpringBlog.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId){
        return new ResponseEntity<>(postService.findAllPostsByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/createPost/{userId}")
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
