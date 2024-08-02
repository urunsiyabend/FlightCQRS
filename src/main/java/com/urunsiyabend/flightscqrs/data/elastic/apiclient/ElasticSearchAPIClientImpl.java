package com.urunsiyabend.flightscqrs.data.elastic.apiclient;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchAPIClientImpl implements ElasticSearchAPIClient {

    private final RestHighLevelClient restHighLevelClient;

    public ElasticSearchAPIClientImpl(@Value("${elasticsearch.host}") String ELASTICSEARCH_HOST,
                                      @Value("${elasticsearch.port}") int ELASTICSEARCH_PORT) {
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, "http")
                )
        );
    }

    @Override
    public RestHighLevelClient getRestHighLevelClient() {
        return restHighLevelClient;
    }
}
