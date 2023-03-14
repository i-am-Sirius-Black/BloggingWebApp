package com.blog.api.services;

import com.blog.api.payloads.CommentDto;


public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}
