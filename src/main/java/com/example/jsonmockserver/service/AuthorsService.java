package com.example.jsonmockserver.service;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Authors;
import com.example.jsonmockserver.dto.requests.AddAuthorRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorsService {
    Authors addAuthor(AddAuthorRequest addAuthorRequest) throws InvalidDataException;

    Authors getAuthor(Long authorId) throws InvalidDataException;

    void deleteAuthor(Long authorId) throws InvalidDataException;

    List<Authors> getFilteredAuthors() throws InvalidDataException;

    List<Authors> getAllAuthors() throws InvalidDataException;
}
