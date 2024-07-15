package com.olamaps.consumermaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoCompleteResponse {
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("info_messages")
    private ArrayList<String> infoMessages;
    @JsonProperty("predictions")
    private ArrayList<Prediction> predictions;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("status")
    private String status;
}
