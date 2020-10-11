package com.example.jsonmockserver.service;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FileDataService {

    FileData getFileData() throws InvalidDataException;

    ResponseEntity<Response> clearFileData() throws InvalidDataException;
}
