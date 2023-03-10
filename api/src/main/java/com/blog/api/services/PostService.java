package com.blog.api.services;


import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //getALL post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get singlePost
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostByCategory(Integer userId,Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get all post by user
    List<PostDto> getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //search
    List<PostDto> searchPosts(String keyword);


}
