package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Viewport {
    @JsonProperty("southwest")
    private Coordinate southwest;
    @JsonProperty("northeast")
    private Coordinate northeast;
}
