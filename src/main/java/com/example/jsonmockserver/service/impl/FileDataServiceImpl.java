package com.example.jsonmockserver.service.impl;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.helper.StoredFileDeserializer;
import com.example.jsonmockserver.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public FileData getFileData() throws InvalidDataException {
        return storedFileDeserializer.getFileData();
    }

    @Override
    public void clearFileData() throws InvalidDataException {
        storedFileDeserializer.clearFileData();
    }
}
