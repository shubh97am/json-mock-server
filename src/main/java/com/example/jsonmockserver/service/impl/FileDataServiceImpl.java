package com.example.jsonmockserver.service.impl;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.helper.APIResponse;
import com.example.jsonmockserver.helper.StoredFileDeserializer;
import com.example.jsonmockserver.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FileDataServiceImpl implements FileDataService {
    private final StoredFileDeserializer storedFileDeserializer;

    @Autowired
    public FileDataServiceImpl(StoredFileDeserializer storedFileDeserializer) {
        this.storedFileDeserializer = storedFileDeserializer;
    }

    @Override
    public ResponseEntity<Response> getFileData() throws InvalidDataException {
        return APIResponse.renderSuccess(storedFileDeserializer.getFileData(), 100, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> clearFileData() throws InvalidDataException {
        return null;
    }
}
