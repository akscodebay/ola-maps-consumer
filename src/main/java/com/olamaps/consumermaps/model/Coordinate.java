package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinate {
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lng")
    private double longitude;
}
