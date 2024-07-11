package com.olamaps.consumermaps.controller;

import com.olamaps.consumermaps.service.MapsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Controller {

    private final MapsService mapsService;

    public Controller (MapsService mapsService) {
        this.mapsService = mapsService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> getHome(){
        return new ResponseEntity<>("This is dummy home", HttpStatus.OK);
    }

    @GetMapping("/places/auto-complete-suggestions")
    public ResponseEntity<String> getAutoCompleteSuggestions(){
        return new ResponseEntity<>(mapsService.getAutoCompleteSuggestions(), HttpStatus.OK);
    }

}
