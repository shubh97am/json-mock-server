package com.example.jsonmockserver.controller;


import com.example.jsonmockserver.common.annotations.EnableLogging;
import com.example.jsonmockserver.common.constants.Constant;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.Controller.BASE_URL)
public class FileDataController {

    private final FileDataService fileDataService;

    @Autowired
    public FileDataController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @GetMapping("/file-data")
    @EnableLogging
    public ResponseEntity<Response> getFileData() throws InvalidDataException {
        return fileDataService.getFileData();
    }
}
