package com.example.jsonmockserver.controller;


import com.example.jsonmockserver.common.annotations.EnableLogging;
import com.example.jsonmockserver.common.annotations.ValidateRequestAccess;
import com.example.jsonmockserver.common.constants.Constant;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.helper.APIResponse;
import com.example.jsonmockserver.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        FileData fileData = fileDataService.getFileData();
        return APIResponse.renderSuccess(fileData, 100, HttpStatus.OK);
    }

    @PostMapping("/file-data")
    @EnableLogging
    @ValidateRequestAccess
    public ResponseEntity<Response> clearFileData() throws InvalidDataException {
        fileDataService.clearFileData();
        return APIResponse.renderSuccess("Operation Successful", 100, HttpStatus.OK);
    }
}
