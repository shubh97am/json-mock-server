package com.example.jsonmockserver.controller;

import com.example.jsonmockserver.common.annotations.EnableLogging;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.responses.Response;
import com.example.jsonmockserver.helper.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    @EnableLogging
    public ResponseEntity<Response> checkHealth() {
        return APIResponse.renderSuccess("WelCome to Json-Mock-Server|| Developed By Shubham Malav", 100, HttpStatus.OK);
    }
}
