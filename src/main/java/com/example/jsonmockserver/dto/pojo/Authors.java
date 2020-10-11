package com.example.jsonmockserver.dto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Authors {
    private Long id;
    private String firstName;
    private String lastName;
    private Long posts = 0L;

    public Authors() {

    }

    public Authors(Long authorId) {
        this.id = authorId;
    }

    public Authors(Long id, String firstName, String lastName, Long posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = Objects.isNull(posts) ? 0 : posts;
    }
}
