package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StructuredFormat {
    @JsonProperty("main_text_matched_substrings")
    private ArrayList<MatchedSubstring> mainTextMatchedSubstrings;
    @JsonProperty("secondary_text_matched_substrings")
    private ArrayList<MatchedSubstring> secondaryTextMatchedSubstrings;
    @JsonProperty("secondary_text")
    private String secondaryText;
    @JsonProperty("main_text")
    private String mainText;
}
