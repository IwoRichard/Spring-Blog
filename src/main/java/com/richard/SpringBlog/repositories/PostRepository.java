package com.richard.SpringBlog.repositories;

import com.richard.SpringBlog.entities.Post;
import com.richard.SpringBlog.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.author = :author ORDER BY p.createdDate DESC")
    List<Post> findByAuthorOrderByCreatedDateDesc(User author);
}
