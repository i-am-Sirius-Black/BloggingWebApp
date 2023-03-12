package com.blog.api.controllers;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.CommentDto;
import com.blog.api.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/{userId}/comments")
    public ResponseEntity<CommentDto> createComment( @RequestBody CommentDto comment, @PathVariable Integer postId,@PathVariable Integer userId){

        CommentDto createComment = this.commentService.createComment(comment, postId, userId);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfull!!",true), HttpStatus.OK);

    }
}
