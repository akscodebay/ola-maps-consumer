package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse {
    private String request_id;
    private String status;
    private String reason;
    private ArrayList<GeocodingResult> geocodingResults;
}
