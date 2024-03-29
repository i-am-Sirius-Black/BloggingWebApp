package com.blog.api.services.impl;

import com.blog.api.entities.Category;
import com.blog.api.entities.Post;
import com.blog.api.entities.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.PostDto;
import com.blog.api.payloads.PostResponse;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.repositories.PostRepo;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort =null;

        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }

        Pageable p = PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPosts= pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse =new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id",categoryId));

        Sort sort =null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }

        Pageable p =PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page =this.postRepo.findByCategory(cat,p);
        List<Post> posts =page.getContent();

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));

        Sort sort =null;
        if(sortDir.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page = this.postRepo.findByUser(user,p);
        List<Post> posts=page.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);

        if(posts.isEmpty()){
            throw new ResourceNotFoundException("Given Keyword","keyword: "+keyword +" ",0);
        }
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
}
