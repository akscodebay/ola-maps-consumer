package com.olamaps.consumermaps.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutoCompleteRequest {
    @NotNull(message = "Input cannot be null")
    @Size(min = 3)
    private String input;
    private String origin;
    private String location;
    private Integer radius;
    private Boolean strictBounds = Boolean.FALSE;
}
