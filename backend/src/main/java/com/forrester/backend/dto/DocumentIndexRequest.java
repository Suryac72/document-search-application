package com.forrester.backend.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object for Document Indexing Request
 */
@Data
public class DocumentIndexRequest {
    @NotBlank(message = "Document Title is required")
    private String documentTitle;
    
    @NotBlank(message = "Document Content is required")
    private String documentContent;
    
    private String documentAuthor;
    private String documentCategory;
}