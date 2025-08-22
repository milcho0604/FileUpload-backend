package com.example.flow.file.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class FileController {

    @GetMapping("health")
    public String healthCheck() {
        return "server ok";
    }
}
