package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    @JsonProperty("location")
    private Coordinate location;
    @JsonProperty("location_type")
    private String locationType;
    @JsonProperty("viewport")
    private Viewport viewport;
}
