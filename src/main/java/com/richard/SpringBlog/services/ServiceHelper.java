package com.richard.SpringBlog.services;

import com.richard.SpringBlog.entities.Comment;
import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.entities.User;
import com.richard.SpringBlog.exceptions.ResourceNotFoundException;
import com.richard.SpringBlog.repositories.CommentRepository;
import com.richard.SpringBlog.repositories.PostRepository;
import com.richard.SpringBlog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;


    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with ID - " + userId + " not found"));
    }

    public Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post with ID - " + id + " not found"));
    }

    public Comment findCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment with ID - " + id + " not found"));
    }
}
