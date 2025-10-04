package com.forrester.backend.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forrester.backend.dto.DocumentIndexRequest;
import com.forrester.backend.dto.DocumentSearchRequest;
import com.forrester.backend.dto.DocumentSearchResponse;
import com.forrester.backend.services.DocumentOpenSearchService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DocumentSearchController {

    private final DocumentOpenSearchService searchService;

    /**
     * Controller method to index new document
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/index")
    public ResponseEntity<Map<String, String>> indexDocument(
            @Valid @RequestBody DocumentIndexRequest request) throws IOException {
        String docId = searchService.indexDocument(request);
        return ResponseEntity.ok(Map.of(
                "id", docId,
                "message", "Document indexed successfully"
        ));
    }

    /**
     * Controller method to search documents
     * @param q
     * @param from
     * @param size
     * @param category
     * @return
     * @throws IOException
     */
    @GetMapping("/search")
    public ResponseEntity<DocumentSearchResponse> searchDocuments(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category) throws IOException {

        DocumentSearchRequest request = new DocumentSearchRequest();
        request.setQuery(q);
        request.setFrom(from);
        request.setSize(size);
        request.setCategory(category);

        DocumentSearchResponse response = searchService.searchDocuments(request);
        return ResponseEntity.ok(response);
    }
}
