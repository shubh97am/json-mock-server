package com.example.jsonmockserver.controller;

import com.example.jsonmockserver.common.annotations.EnableLogging;
import com.example.jsonmockserver.common.constants.Constant;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Authors;
import com.example.jsonmockserver.dto.requests.AddAuthorRequest;
import com.example.jsonmockserver.dto.responses.AuthorsResponse;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.helper.APIResponse;
import com.example.jsonmockserver.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Constant.Controller.BASE_URL)
public class AuthorController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping("/authors")
    @EnableLogging
    public ResponseEntity<Response> addAuthor(@RequestBody AddAuthorRequest addAuthorRequest) throws InvalidDataException {
        Authors authors = authorsService.addAuthor(addAuthorRequest);
        AuthorsResponse authorsResponse = new AuthorsResponse(Collections.singletonList(authors));
        return APIResponse.renderSuccess(authorsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/authors/{author_id}")
    @EnableLogging
    public ResponseEntity<Response> getAuthors(@PathVariable(value = "author_id") Long authorId) throws InvalidDataException {
        Authors authors = authorsService.getAuthor(authorId);
        AuthorsResponse authorsResponse = new AuthorsResponse(Collections.singletonList(authors));
        return APIResponse.renderSuccess(authorsResponse, 100, HttpStatus.OK);
    }

    @DeleteMapping("/authors/{author_id}")
    @EnableLogging
    public ResponseEntity<Response> removeAuthor(@PathVariable(value = "author_id") Long authorId) throws InvalidDataException {
        authorsService.deleteAuthor(authorId);
        return APIResponse.renderSuccess("Author Deleted SuccessFully", 100, HttpStatus.OK);
    }

    @GetMapping("/authors")
    @EnableLogging
    public ResponseEntity<Response> getFilterAuthors(@RequestParam(value = "author_ids", required = false) String author_ids,
                                                     @RequestParam(value = "title", required = false) String title,
                                                     @RequestParam(value = "author", required = false) String author) throws InvalidDataException {
        List<Authors> authors = authorsService.getFilteredAuthors();
        AuthorsResponse authorsResponse = new AuthorsResponse(authors);
        return APIResponse.renderSuccess(authorsResponse, 100, HttpStatus.OK);
    }

    @GetMapping("/authors/all")
    @EnableLogging
    public ResponseEntity<Response> getAllAuthors() throws InvalidDataException {
        List<Authors> authors = authorsService.getAllAuthors();
        AuthorsResponse authorsResponse = new AuthorsResponse(authors);
        return APIResponse.renderSuccess(authorsResponse, 100, HttpStatus.OK);
    }


}
