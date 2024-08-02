package com.urunsiyabend.flightscqrs.data.elastic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urunsiyabend.flightscqrs.data.elastic.apiclient.ElasticSearchAPIClient;
import lombok.SneakyThrows;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private final ObjectMapper objectMapper;
    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticSearchServiceImpl(ElasticSearchAPIClient elasticsearchApiClient, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = elasticsearchApiClient.getRestHighLevelClient();
    }

    @Override
    public <TSource> void insertDocument(String indexName, String documentUniqueId, TSource source) throws IOException {
        String jsonData = objectMapper.writeValueAsString(source);

        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(documentUniqueId)
                .source(jsonData, XContentType.JSON);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public <TSource> List<TSource> search(String indexName, Class<TSource> clazz) throws IOException, Exception {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return Stream
                .of(searchResponse.getHits().getHits())
                .map(hit -> convert(hit.getSourceAsString(), clazz))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDocument(String indexName, String documentUniqueId) throws IOException {
        restHighLevelClient.delete(new DeleteRequest(indexName, documentUniqueId), RequestOptions.DEFAULT);
    }

    @SneakyThrows
    private <T> T convert(String source, Class<T> clazz) {
        return objectMapper.readValue(source, clazz);
    }
}
