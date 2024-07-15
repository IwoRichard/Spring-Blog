package com.richard.SpringBlog.services.interfaces;

import com.richard.SpringBlog.entities.Comment;

public interface CommentService {

    Comment addComment(Long postId, Long userId, String content);

    String deleteComment(Long commentId, Long userId);
}
