package com.example.jsonmockserver.controller;

import com.example.jsonmockserver.common.annotations.EnableLogging;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.BuildPostFilterSortRequest;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import com.example.jsonmockserver.dto.requests.UpdatePostRequest;
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

import static com.example.jsonmockserver.common.constants.Constant.Controller;
import static com.example.jsonmockserver.common.constants.Constant.PostControllerConstants.*;
import static com.example.jsonmockserver.common.constants.Constant.Fields.*;

@RestController
@RequestMapping(Controller.BASE_URL)
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping(ADD_POST)
    @EnableLogging
    public ResponseEntity<Response> addPost(@RequestBody AddPostRequest addPostRequest) throws InvalidDataException {
        Posts posts = postsService.addPost(addPostRequest);
        PostsResponse postsResponse = new PostsResponse(Collections.singletonList(posts));
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping(GET_POST)
    @EnableLogging
    public ResponseEntity<Response> getPost(@PathVariable(value = POST_ID) Long postId) throws InvalidDataException {
        Posts posts = postsService.getPost(postId);
        PostsResponse postsResponse = new PostsResponse(Collections.singletonList(posts));
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_POST)
    @EnableLogging
    public ResponseEntity<Response> removePost(@PathVariable(value = POST_ID) Long postId) throws InvalidDataException {
        postsService.deletePost(postId);
        return APIResponse.renderSuccess("Post Deleted SuccessFully", 100, HttpStatus.OK);
    }


    @PatchMapping(UPDATE_POST)
    @EnableLogging
    public ResponseEntity<Response> updatePost(@PathVariable(value = POST_ID) Long postId, @RequestBody UpdatePostRequest updatePostRequest) throws InvalidDataException {
        Posts posts = postsService.updatePost(postId, updatePostRequest);
        PostsResponse postsResponse = new PostsResponse(Collections.singletonList(posts));
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping(GET_ALL_POST)
    @EnableLogging
    public ResponseEntity<Response> getAllPosts() throws InvalidDataException {
        List<Posts> posts = postsService.getAllPosts();
        PostsResponse postsResponse = new PostsResponse(posts);
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/posts")
    @EnableLogging
    public ResponseEntity<Response> getFilterPosts(@RequestParam(value = "title", required = false, defaultValue = "") String title,
                                                   @RequestParam(value = "author", required = false, defaultValue = "") String author,
                                                   @RequestParam(value = "_order", required = false, defaultValue = "asc") String sortType,
                                                   @RequestParam(value = "_sort", required = false, defaultValue = "id") String sortingField) throws InvalidDataException {
        BuildPostFilterSortRequest request = new BuildPostFilterSortRequest(title, author, sortType, sortingField);
        List<Posts> posts = postsService.getFilteredPosts(request);
        PostsResponse postsResponse = new PostsResponse(posts);
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/posts/search")
    @EnableLogging
    public ResponseEntity<Response> searchPosts(@RequestParam(value = "query", required = false, defaultValue = "") String searchText) throws InvalidDataException {
        List<Posts> posts = postsService.searchPosts(searchText);
        PostsResponse postsResponse = new PostsResponse(posts);
        return APIResponse.renderSuccess(postsResponse, 100, HttpStatus.OK);
    }

}
