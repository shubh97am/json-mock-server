package com.example.jsonmockserver.service.impl;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Authors;
import com.example.jsonmockserver.dto.requests.AddAuthorRequest;
import com.example.jsonmockserver.helper.AuthorBuilder;
import com.example.jsonmockserver.service.AuthorsService;
import com.example.jsonmockserver.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorValidator authorValidator;

    private final AuthorBuilder authorBuilder;

    @Autowired
    public AuthorsServiceImpl(AuthorValidator authorValidator, AuthorBuilder authorBuilder) {
        this.authorValidator = authorValidator;
        this.authorBuilder = authorBuilder;
    }

    @Override
    public Authors addAuthor(AddAuthorRequest addAuthorRequest) throws InvalidDataException {
        authorValidator.validateAddAuthorRequest(addAuthorRequest);
        Authors author = authorBuilder.buildAuthor(addAuthorRequest);
        authorBuilder.saveAuthorToFile(author);
        return author;
    }

    @Override
    public Authors getAuthor(Long authorId) throws InvalidDataException {
        return authorBuilder.getAuthor(authorId);
    }

    @Override
    public void deleteAuthor(Long authorId) throws InvalidDataException {
        authorBuilder.deleteAuthorFromFile(authorId);
    }

    @Override
    public List<Authors> getFilteredAuthors() throws InvalidDataException {
        return new ArrayList<>();
    }

    @Override
    public List<Authors> getAllAuthors() throws InvalidDataException {
        return authorBuilder.getAllAuthors();
    }
}
