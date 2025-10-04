package com.forrester.backend.dto;

import lombok.Data;

/**
 * Data Transfer Object for Document Search Request
 */
@Data
public class DocumentSearchRequest {
    private String query;
    private Integer from = 0;
    private Integer size = 10;
    private String category;
    private Boolean useSemanticSearch = false;
}
