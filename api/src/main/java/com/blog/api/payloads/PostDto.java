package com.blog.api.payloads;

import com.blog.api.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;

    @NotBlank
    @Size(min = 5, max = 100,message = "size must be greater than 5 and smaller than 100 !! ")
    private String title;
    @NotBlank
    @Size(min = 100, max = 10000,message = "size must be greater than 100 and smaller than 10000 !! ")
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments =new HashSet<>();


}
