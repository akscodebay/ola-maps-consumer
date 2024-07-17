package com.olamaps.consumermaps.controller;

import com.olamaps.consumermaps.exception.AutoCompleteException;
import com.olamaps.consumermaps.model.AutoCompleteRequest;
import com.olamaps.consumermaps.model.AutoCompleteResponse;
import com.olamaps.consumermaps.service.MapsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/places/auto-complete-suggestions")
    public ResponseEntity<AutoCompleteResponse> getAutoCompleteSuggestions(@Valid @RequestBody AutoCompleteRequest autoCompleteRequest) throws AutoCompleteException {
        return new ResponseEntity<>(mapsService.getAutoCompleteSuggestions(autoCompleteRequest), HttpStatus.OK);
    }

}
