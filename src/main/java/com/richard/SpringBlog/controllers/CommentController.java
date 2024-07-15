package com.richard.SpringBlog.controllers;

import com.richard.SpringBlog.entities.Comment;
import com.richard.SpringBlog.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(
            @RequestParam("postId") Long postId,
            @RequestParam("userId") Long userId,
            @RequestBody(required = true) Map<String, String> requestBody){

        String content = requestBody.get("content");
        Comment newComment = commentService.addComment(postId, userId, content);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}
