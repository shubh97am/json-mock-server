package com.example.jsonmockserver.dto.responses;

import com.example.jsonmockserver.dto.pojo.Posts;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostsResponse {
    List<Posts> posts;

    public PostsResponse() {

    }

    public PostsResponse(List<Posts> posts) {
        this.posts = posts;
    }
}
