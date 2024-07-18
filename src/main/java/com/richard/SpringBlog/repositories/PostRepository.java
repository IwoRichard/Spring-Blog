package com.richard.SpringBlog.repositories;

import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.author = :author ORDER BY p.createdDate DESC")
    Page<Post> findByAuthorOrderByCreatedDateDesc(User author, Pageable pageable);
}
