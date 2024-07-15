package com.richard.SpringBlog.services;

import com.richard.SpringBlog.entities.Comment;
import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.entities.User;
import com.richard.SpringBlog.exceptions.ResourceNotFoundException;
import com.richard.SpringBlog.exceptions.UnauthorizedException;
import com.richard.SpringBlog.repositories.CommentRepository;
import com.richard.SpringBlog.repositories.PostRepository;
import com.richard.SpringBlog.repositories.UserRepository;
import com.richard.SpringBlog.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ServiceHelper serviceHelper;

    @Override
    public Comment addComment(Long postId, Long userId, String content) {
        User user = serviceHelper.findUserById(userId);
        Post post = serviceHelper.findPostById(postId);

        Comment newComment = Comment.builder()
                .content(content)
                .post(post)
                .author(user)
                .createdDate(LocalDateTime.now())
                .build();
        commentRepository.save(newComment);
        return newComment;
    }

    @Override
    public String deleteComment(Long commentId, Long userId) {
        User user = serviceHelper.findUserById(userId);
        Comment comment = serviceHelper.findCommentById(commentId);

        if (comment.getAuthor().equals(user)){
            commentRepository.delete(comment);
        }else {
            throw new UnauthorizedException("USER WITH ID - " + userId + " NOT AUTHORISED");
        }
        return "Comment Deleted Successfully";
    }
}
