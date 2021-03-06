package com.example.jsonmockserver.service.impl;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.BuildPostFilterSortRequest;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import com.example.jsonmockserver.dto.requests.UpdatePostRequest;
import com.example.jsonmockserver.helper.PostBuilder;
import com.example.jsonmockserver.service.PostsService;
import com.example.jsonmockserver.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostValidator postValidator;
    private final PostBuilder postBuilder;

    @Autowired
    public PostsServiceImpl(PostValidator postValidator, PostBuilder postBuilder) {
        this.postValidator = postValidator;
        this.postBuilder = postBuilder;
    }

    @Override
    public Posts addPost(AddPostRequest addPostRequest) throws InvalidDataException {
        postValidator.validateAddPostRequest(addPostRequest);
        Posts posts = postBuilder.buildPost(addPostRequest);
        postBuilder.savePostToFile(posts);
        return posts;

    }

    @Override
    public Posts getPost(Long postId) throws InvalidDataException {
        return postBuilder.getPost(postId);
    }

    @Override
    public void deletePost(Long postId) throws InvalidDataException {
        postBuilder.deletePostFromFile(postId);
    }

    @Override
    public List<Posts> getFilteredPosts(BuildPostFilterSortRequest request) throws InvalidDataException {
        postValidator.validateBuildPostFilterSortRequest(request);
        return postBuilder.getFilteredAndSortedPost(request);
    }

    @Override
    public List<Posts> searchPosts(String query) throws InvalidDataException {
        return postBuilder.searchPosts(query);
    }

    @Override
    public List<Posts> getAllPosts() throws InvalidDataException {
        return postBuilder.getAllPosts();
    }

    @Override
    public Posts updatePost(Long postId, UpdatePostRequest updatePostRequest) throws InvalidDataException {
        return postBuilder.updatePost(postId, updatePostRequest);
    }
}
