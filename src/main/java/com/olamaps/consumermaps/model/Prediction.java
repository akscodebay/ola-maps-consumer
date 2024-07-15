package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediction {
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("types")
    private ArrayList<String> types;
    @JsonProperty("matched_substrings")
    private ArrayList<MatchedSubstring> matchedSubstrings;
    @JsonProperty("terms")
    private ArrayList<Term> terms;
    @JsonProperty("distance_meters")
    private Integer distanceMeters;
    @JsonProperty("structured_formatting")
    private StructuredFormat structuredFormat;
    @JsonProperty("description")
    private String description;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("layer")
    private ArrayList<String> layer;
}
