package com.example.jsonmockserver.validator;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Authors;
import com.example.jsonmockserver.dto.requests.AddAuthorRequest;
import com.example.jsonmockserver.dto.requests.UpdateAuthorRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthorValidator {

    public void validateAddAuthorRequest(AddAuthorRequest addAuthorRequest) throws InvalidDataException {
        if (StringUtils.isEmpty(addAuthorRequest)) {
            throw new InvalidDataException("Invalid (null) Add Author Request");
        }
        if (StringUtils.isEmpty(addAuthorRequest.getFirstName())) {
            throw new InvalidDataException("Missing Required Field | First Name");
        }
    }


    public void validateUpdateAuthorRequest(Authors authors, UpdateAuthorRequest updateAuthorRequest) throws InvalidDataException {
        if (!StringUtils.isEmpty(updateAuthorRequest.getFirstName())) {
            authors.setFirstName(updateAuthorRequest.getFirstName());
        }

        if (!StringUtils.isEmpty(updateAuthorRequest.getLastName())) {
            authors.setLastName(updateAuthorRequest.getLastName());
        }
        if (!StringUtils.isEmpty(updateAuthorRequest.getPosts())) {
            authors.setPosts(updateAuthorRequest.getPosts());
        }

    }
}
