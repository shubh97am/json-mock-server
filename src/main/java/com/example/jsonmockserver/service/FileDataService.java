package com.example.jsonmockserver.service;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.responses.Response;
import org.springframework.http.ResponseEntity;

public interface FileDataService {

    ResponseEntity<Response> getFileData() throws InvalidDataException;
    ResponseEntity<Response> clearFileData() throws InvalidDataException;
}
