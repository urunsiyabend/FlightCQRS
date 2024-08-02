package com.urunsiyabend.flightscqrs.data.elastic.apiclient;

import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticSearchAPIClient {
    RestHighLevelClient getRestHighLevelClient();
}
