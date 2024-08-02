package com.urunsiyabend.flightscqrs.mq.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;
import com.urunsiyabend.flightscqrs.data.elastic.service.ElasticSearchService;
import com.urunsiyabend.flightscqrs.entities.PlaneType;
import com.urunsiyabend.flightscqrs.mq.receiver.RabbitMQReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Lazy(value = false)
public class ElasticSearchDeleter {
    private final ElasticSearchService elasticSearchService;
    private final ObjectMapper objectMapper;
    private final String PLANE_TYPE_DELETED_QUEUE = "PLANE_TYPE_DELETED_QUEUE";

    @Autowired
    public ElasticSearchDeleter(RabbitMQReceiver rabbitMQReceiver, ElasticSearchService elasticSearchService, ObjectMapper objectMapper) throws Exception {
        this.elasticSearchService = elasticSearchService;
        this.objectMapper = objectMapper;

        rabbitMQReceiver.receive(PLANE_TYPE_DELETED_QUEUE, this::deletePlaneType);
    }

    private void deletePlaneType(String consumerTag, Delivery message) throws IOException {
        String id = objectMapper.readValue(message.getBody(), String.class);

        elasticSearchService.deleteDocument("plane-type", id);
    }
}
