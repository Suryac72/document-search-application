package com.forrester.backend.dto;

import java.util.List;

import com.forrester.backend.models.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object for Document Search Response 
 */
@Data
@AllArgsConstructor
public class DocumentSearchResponse {
    private long totalHits;
    private List<SearchHit> hits;
    private long took;

    @Data
    @AllArgsConstructor
    public static class SearchHit {
        private Document document;
        private float score;
    }
}