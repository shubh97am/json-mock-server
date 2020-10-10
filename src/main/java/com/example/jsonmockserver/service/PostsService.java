package com.example.jsonmockserver.service;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;

import java.util.List;


public interface PostsService {
    Posts addPost(AddPostRequest addPostRequest) throws InvalidDataException;
    Posts getPost(Long postId) throws InvalidDataException;
    List<Posts> getFilteredPosts() throws InvalidDataException;
    List<Posts> getAllPosts() throws InvalidDataException;
}
