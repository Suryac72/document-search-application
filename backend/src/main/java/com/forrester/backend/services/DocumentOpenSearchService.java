package com.forrester.backend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.forrester.backend.dto.DocumentIndexRequest;
import com.forrester.backend.dto.DocumentSearchRequest;
import com.forrester.backend.dto.DocumentSearchResponse;
import com.forrester.backend.models.Document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentOpenSearchService {

    private final OpenSearchClient client;

    @Value("${opensearch.index-name}")
    private String indexName;

    /**
     * Method to index document on opensearch
     * @param request
     * @return
     * @throws IOException
     */
    public String indexDocument(DocumentIndexRequest request) throws IOException {
        Document doc = new Document();
        doc.setDocumentId(UUID.randomUUID().toString());
        doc.setDocumentTitle(request.getDocumentTitle());
        doc.setDocumentContent(request.getDocumentContent());
        doc.setDocumentAuthor(request.getDocumentAuthor());
        doc.setDocumentCategory(request.getDocumentCategory());
        doc.setCreatedAt(new Date());

        IndexRequest<Document> indexRequest = new IndexRequest.Builder<Document>()
                .index(indexName)
                .id(doc.getDocumentId())
                .document(doc)
                .build();

        IndexResponse response = client.index(indexRequest);
        log.info("Document created and indexed with documentId : {}", response.id());

        return response.id();
    }

    /**
     * Method to search documents on opensearch
     * @param request
     * @return
     * @throws IOException
     */
    public DocumentSearchResponse searchDocuments(DocumentSearchRequest request) throws IOException {

        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        if (request.getQuery() != null && !request.getQuery().isEmpty()) {
            boolQuery.should(q -> q 
                .match(m -> m
                    .field("documentTitle")
                    .query(FieldValue.of(request.getQuery()))
                    .boost(2.0f)
                )
            );
            boolQuery.should(q -> q 
                .match(m -> m
                    .field("documentContent")
                    .query(FieldValue.of(request.getQuery()))
                )
            );
            boolQuery.minimumShouldMatch("1");
        } else {
            boolQuery.must(q -> q
                .matchAll(m -> m)
            );
        }

        if (request.getCategory() != null && !request.getCategory().isEmpty()) {
            boolQuery.filter(q -> q
                .term(t -> t
                    .field("documentCategory.keyword")
                    .value(FieldValue.of(request.getCategory()))
                )
            );
        }

        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(indexName)
                .from(request.getFrom())
                .size(request.getSize())
                .query(q -> q.bool(boolQuery.build()))
                .build();

        SearchResponse<Document> response = client.search(searchRequest, Document.class);
        
        List<DocumentSearchResponse.SearchHit> hits = new ArrayList<>();
        for (Hit<Document> hit : response.hits().hits()) {
            float score = 0.0f;
            Double hitScore = (hit != null) ? hit.score() : null;
            if (hitScore != null) {
                score = hitScore.floatValue();
            }
        
            hits.add(new DocumentSearchResponse.SearchHit(hit != null ? hit.source() : null, score));
        }
        long totalHits = response.hits().total() != null ? response.hits().total().value() : hits.size();

        return new DocumentSearchResponse(
                totalHits,
                hits,
                response.took()
        );
    }
}
