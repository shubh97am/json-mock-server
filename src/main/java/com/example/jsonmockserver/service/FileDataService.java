package com.example.jsonmockserver.service;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.FileData;
import org.springframework.stereotype.Service;

@Service
public interface FileDataService {

    FileData getFileData() throws InvalidDataException;

    void clearFileData() throws InvalidDataException;
}
