package com.olamaps.consumermaps.service;

import com.olamaps.consumermaps.model.AutoCompleteRequest;
import com.olamaps.consumermaps.model.AutoCompleteResponse;
import com.olamaps.consumermaps.restclient.AutoCompleteRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MapsService {

   private final AutoCompleteRestClient autoCompleteRestClient;

    public MapsService(AutoCompleteRestClient autoCompleteRestClient) {
        this.autoCompleteRestClient = autoCompleteRestClient;
    }

    public ResponseEntity<AutoCompleteResponse> getAutoCompleteSuggestions(AutoCompleteRequest autoCompleteRequest) {
        ResponseEntity<AutoCompleteResponse> response = autoCompleteRestClient.getAutoComplete(autoCompleteRequest);
        return response;
    }


}
