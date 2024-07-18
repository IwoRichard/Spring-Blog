package com.richard.SpringBlog.services;

import com.richard.SpringBlog.dtos.PostDto;
import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.entities.User;
import com.richard.SpringBlog.exceptions.UnauthorizedException;
import com.richard.SpringBlog.repositories.PostRepository;
import com.richard.SpringBlog.repositories.UserRepository;
import com.richard.SpringBlog.services.interfaces.FirebaseStorageService;
import com.richard.SpringBlog.services.interfaces.PostService;
import com.richard.SpringBlog.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    @Autowired
    private ServiceHelper serviceHelper;

    @Override
    public Post createPost(Long userId, PostDto postDto, MultipartFile file) {
        User user = serviceHelper.findUserById(userId);
        String fileUrl = firebaseStorageService.uploadFile(file, "PostPictures", user.getProfileName());

        Post newPost = new Post();
        newPost.setPostTitle(postDto.getPostTitle());
        newPost.setPostContent(postDto.getPostContent());
        newPost.setPostPictureUrl(fileUrl);
        newPost.setCreatedDate(LocalDateTime.now());
        newPost.setAuthor(user);

        postRepository.save(newPost);

        return newPost;
    }

    @Override
    public Post editPost(Long userId, Long postId, PostDto postDto) {
        User user = serviceHelper.findUserById(userId);
        Post post = serviceHelper.findPostById(postId);

        if (post.getAuthor().equals(user)){
            post.setPostTitle(post.getPostTitle());
            post.setPostContent(postDto.getPostContent());
            post.setEditedDate(LocalDateTime.now());
            postRepository.save(post);
        }else {
            throw new UnauthorizedException("USER WITH ID - " + userId + " NOT AUTHORISED");
        }
        return post;
    }

    @Override
    public String deletePost(Long userId, Long postId) {
        User user = serviceHelper.findUserById(userId);
        Post post = serviceHelper.findPostById(postId);

        if (post.getAuthor().equals(user)){
            postRepository.delete(post);
        }else {
            throw new UnauthorizedException("USER WITH ID - " + userId + " NOT AUTHORISED");
        }
        return "Post Deleted Successfully";
    }

    @Override
    public Page<Post> findAllPostsByUser(Long userId, int pageNo, int pageSize) {
        User user = serviceHelper.findUserById(userId);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return postRepository.findByAuthorOrderByCreatedDateDesc(user, pageable);
    }

}
