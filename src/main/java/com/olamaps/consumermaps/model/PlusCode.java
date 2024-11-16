package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlusCode {
    @JsonProperty("compound_code")
    private String compoundCode;
    @JsonProperty("global_code")
    private String globalCode;
}
