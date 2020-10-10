package com.example.jsonmockserver.validator;


import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
}
