package com.olamaps.consumermaps.service;

import com.olamaps.consumermaps.exception.AutoCompleteException;
import com.olamaps.consumermaps.model.*;
import com.olamaps.consumermaps.restclient.AddressGeocodeRestClient;
import com.olamaps.consumermaps.restclient.AutoCompleteRestClient;
import org.springframework.stereotype.Service;

@Service
public class MapsService {

   private final AutoCompleteRestClient autoCompleteRestClient;
   private final AddressGeocodeRestClient addressGeocodeRestClient;

    public MapsService(AutoCompleteRestClient autoCompleteRestClient,
                       AddressGeocodeRestClient addressGeocodeRestClient) {
        this.autoCompleteRestClient = autoCompleteRestClient;
        this.addressGeocodeRestClient = addressGeocodeRestClient;
    }

    public AutoCompleteResponse getAutoCompleteSuggestions(AutoCompleteRequest autoCompleteRequest) throws AutoCompleteException {
        AutoCompleteResponse autoCompleteResponse = autoCompleteRestClient.getAutoComplete(autoCompleteRequest);
        if (autoCompleteResponse == null) {
            throw new AutoCompleteException("Auto complete response body is null");
        }
        autoCompleteResponse.setRequestId(CorrelationId.getCorrelationId());
        return autoCompleteResponse;
    }

    public GeocodeResponse getAddressGeocode(GeocodeRequest geocodeRequest) {
        return addressGeocodeRestClient.getAddressGeocode(geocodeRequest);
    }


}
