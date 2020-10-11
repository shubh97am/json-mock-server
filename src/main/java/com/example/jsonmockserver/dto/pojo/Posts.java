package com.example.jsonmockserver.dto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Posts {
    private Long id;
    private String title;
    private String author;
    private Long views = 0L;
    private Long reviews = 0L;

    public Posts() {

    }

    public Posts(Long postId) {
        id = postId;
    }

    public Posts(Long id, String title, String author, Long views, Long reviews) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.views = views;
        this.reviews = reviews;
    }
}
