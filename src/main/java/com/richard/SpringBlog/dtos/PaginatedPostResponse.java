package com.richard.SpringBlog.dtos;


import com.richard.SpringBlog.entities.Post;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedPostResponse {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private List<Post> content;
}
