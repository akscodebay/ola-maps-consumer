package com.olamaps.consumermaps.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GeocodeRequest {
    @NotNull(message = "Address cannot be null")
    @Size(min = 3, message = "Address value should be greater than 3 characters")
    private String address;
    private String bounds;
    private String language;
}
