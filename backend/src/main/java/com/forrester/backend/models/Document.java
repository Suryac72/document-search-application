package com.forrester.backend.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Document model representing a document with its details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private String documentId;
    private String documentTitle;
    private String documentContent;
    private Date createdAt;
    private String documentCategory;
    private String documentAuthor;
}
