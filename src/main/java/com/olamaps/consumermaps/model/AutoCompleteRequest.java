package com.olamaps.consumermaps.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoCompleteRequest {
    @NotNull(message = "Input cannot be null")
    private String input;
    private String origin;
    private String location;
    private Integer radius;
    private Boolean strictBounds;
}
