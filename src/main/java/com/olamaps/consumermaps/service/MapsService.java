package com.olamaps.consumermaps.service;

import com.olamaps.consumermaps.exception.AutoCompleteException;
import com.olamaps.consumermaps.model.AutoCompleteRequest;
import com.olamaps.consumermaps.model.AutoCompleteResponse;
import com.olamaps.consumermaps.model.CorrelationId;
import com.olamaps.consumermaps.restclient.AutoCompleteRestClient;
import org.springframework.stereotype.Service;

@Service
public class MapsService {

   private final AutoCompleteRestClient autoCompleteRestClient;

    public MapsService(AutoCompleteRestClient autoCompleteRestClient) {
        this.autoCompleteRestClient = autoCompleteRestClient;
    }

    public AutoCompleteResponse getAutoCompleteSuggestions(AutoCompleteRequest autoCompleteRequest) throws AutoCompleteException {
        AutoCompleteResponse autoCompleteResponse = autoCompleteRestClient.getAutoComplete(autoCompleteRequest);
        if (autoCompleteResponse == null) {
            throw new AutoCompleteException("Auto complete response body is null");
        }
        autoCompleteResponse.setRequestId(CorrelationId.getCorrelationId());
        return autoCompleteResponse;
    }


}
