package com.olamaps.consumermaps.model;

import lombok.Data;

@Data
public class ErrorMessage {
    private String timestamp;
    private String requestId;
    private String message;
    private String status;
    private String path;
}
