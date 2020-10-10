package com.example.jsonmockserver.controller;

import com.example.jsonmockserver.common.annotations.EnableLogging;
import com.example.jsonmockserver.common.constants.Constant;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import com.example.jsonmockserver.dto.responses.PostsResponse;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.helper.APIResponse;
import com.example.jsonmockserver.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Constant.Controller.BASE_URL)
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/posts")
    @EnableLogging
    public ResponseEntity<Response> addPost(@RequestBody AddPostRequest addPostRequest) throws InvalidDataException {
        Posts posts = postsService.addPost(addPostRequest);
        PostsResponse postsResponse = new PostsResponse(Collections.singletonList(posts));
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/posts/{post_id}")
    @EnableLogging
    public ResponseEntity<Response> getPost(@PathVariable(value = "post_id") Long postId) throws InvalidDataException {
        Posts posts = postsService.getPost(postId);
        PostsResponse postsResponse = new PostsResponse(Collections.singletonList(posts));
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/posts")
    @EnableLogging
    public ResponseEntity<Response> getFilterPosts(@RequestParam(value = "post_ids", required = false) String postIds,
                                                   @RequestParam(value = "title", required = false) String title,
                                                   @RequestParam(value = "author", required = false) String author) throws InvalidDataException {
        List<Posts> posts = postsService.getFilteredPosts();
        PostsResponse postsResponse = new PostsResponse(posts);
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/posts/all")
    @EnableLogging
    public ResponseEntity<Response> getAllPosts() throws InvalidDataException {
        List<Posts> posts = postsService.getAllPosts();
        PostsResponse postsResponse = new PostsResponse(posts);
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }
}
