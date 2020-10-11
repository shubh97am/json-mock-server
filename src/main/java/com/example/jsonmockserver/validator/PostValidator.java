package com.example.jsonmockserver.validator;


import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.BuildPostFilterSortRequest;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import com.example.jsonmockserver.dto.requests.UpdatePostRequest;
import com.example.jsonmockserver.enumeration.SortField;
import com.example.jsonmockserver.enumeration.SortType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Component
public class PostValidator {

    public void validateAddPostRequest(AddPostRequest addPostRequest) throws InvalidDataException {
        if (StringUtils.isEmpty(addPostRequest)) {
            throw new InvalidDataException("Invalid (null) Add Post Request");
        }
        if (StringUtils.isEmpty(addPostRequest.getTitle())) {
            throw new InvalidDataException("Missing Required Field | Title");
        }

        if (StringUtils.isEmpty(addPostRequest.getAuthor())) {
            throw new InvalidDataException("Missing Required Field | Author");
        }
        Long reviews = Optional.ofNullable(addPostRequest.getReviews())
                .orElse(0L);
        Long views = Optional.ofNullable(addPostRequest.getViews())
                .orElse(0L);

        if (reviews > views) {
            throw new InvalidDataException("Review Must be less than Views");
        }
    }

    public void validateUpdatePostRequest(Posts posts, UpdatePostRequest updatePostRequest) throws InvalidDataException {
        if (!StringUtils.isEmpty(updatePostRequest.getTitle())) {
            posts.setTitle(updatePostRequest.getTitle());
        }

        if (!StringUtils.isEmpty(updatePostRequest.getAuthor())) {
            posts.setAuthor(updatePostRequest.getAuthor());
        }
        if (!StringUtils.isEmpty(updatePostRequest.getViews())) {
            posts.setViews(updatePostRequest.getViews());
        }

        if (!StringUtils.isEmpty(updatePostRequest.getReviews())) {
            posts.setReviews(updatePostRequest.getReviews());
        }
        Long reviews = Optional.ofNullable(posts.getReviews())
                .orElse(0L);
        Long views = Optional.ofNullable(posts.getViews())
                .orElse(0L);

        if (reviews > views) {
            throw new InvalidDataException("Review Must be less than Views");
        }
    }

    public void validateBuildPostFilterSortRequest(BuildPostFilterSortRequest request) throws InvalidDataException {
        validateSortType(request.getSortType());
        validateSortFields(request.getSortField());
    }

    private void validateSortType(String sortType) throws InvalidDataException {
        SortType.getSortTypeByKey(sortType);
    }

    private void validateSortFields(String sortField) throws InvalidDataException {
        SortField.getSortFieldByKey(sortField);
    }
}
