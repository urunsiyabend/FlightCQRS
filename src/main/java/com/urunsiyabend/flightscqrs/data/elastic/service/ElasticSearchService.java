package com.urunsiyabend.flightscqrs.data.elastic.service;

import java.io.IOException;
import java.util.List;

public interface ElasticSearchService {
    <TSource> void insertDocument(String indexName, String documentUniqueId, TSource source) throws IOException;

    <TSource> List<TSource> search(String indexName, Class<TSource> clazz) throws IOException, Exception;

    void deleteDocument(String indexName, String documentUniqueId) throws IOException;
}