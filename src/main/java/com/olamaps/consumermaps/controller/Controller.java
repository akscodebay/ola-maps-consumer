package com.olamaps.consumermaps.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Controller {

    @GetMapping("/home")
    public ResponseEntity<String> getHome(){
        return new ResponseEntity<>("This is dummy home", HttpStatus.OK);
    }
}
