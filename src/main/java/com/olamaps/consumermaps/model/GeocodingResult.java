package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Array;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingResult {
    @JsonProperty("formatted_address")
    private String formattedAddress;
    @JsonProperty("types")
    private ArrayList<String> types;
    @JsonProperty("name")
    private String name;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("address_components")
    private ArrayList<AddressComponent> addressComponents;
    @JsonProperty("plus_code")
    private PlusCode plusCode;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("layer")
    private ArrayList<String> layer;

}
