package com.example.jsonmockserver.service;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import com.example.jsonmockserver.dto.requests.UpdatePostRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostsService {
    Posts addPost(AddPostRequest addPostRequest) throws InvalidDataException;

    Posts getPost(Long postId) throws InvalidDataException;

    void deletePost(Long postId) throws InvalidDataException;

    List<Posts> getFilteredPosts() throws InvalidDataException;

    List<Posts> getAllPosts() throws InvalidDataException;

    Posts updatePost(Long postId, UpdatePostRequest addPostRequest) throws InvalidDataException;
}
