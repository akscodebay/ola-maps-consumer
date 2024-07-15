package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchedSubstring {
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("length")
    private Integer length;
}
