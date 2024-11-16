package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressComponent {
    @JsonProperty("types")
    private ArrayList<String> types;
    @JsonProperty("short_name")
    private String shortName;
    @JsonProperty("long_name")
    private String longName;
}
