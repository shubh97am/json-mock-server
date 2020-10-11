package com.example.jsonmockserver.dto.responses;

import com.example.jsonmockserver.dto.pojo.Authors;
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
public class AuthorsResponse {

    List<Authors> authors;

    public AuthorsResponse() {

    }

    public AuthorsResponse(List<Authors> authors) {
        this.authors = authors;
    }
}
