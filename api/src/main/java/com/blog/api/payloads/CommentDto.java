package com.blog.api.payloads;

import com.blog.api.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {


    private int id;

    private String content;
    private UserDto user;



}
