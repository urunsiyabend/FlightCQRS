package com.urunsiyabend.flightscqrs.mq.updater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;
import com.urunsiyabend.flightscqrs.data.elastic.service.ElasticSearchService;
import com.urunsiyabend.flightscqrs.entities.PlaneType;
import com.urunsiyabend.flightscqrs.mq.receiver.RabbitMQReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Lazy(value = false)
public class ElasticSearchUpdater {
    private final ElasticSearchService elasticSearchService;
    private final ObjectMapper objectMapper;
    private final String PLANE_TYPE_INSERTED_QUEUE = "PLANE_TYPE_INSERTED_QUEUE";

    Logger logger = LoggerFactory.getLogger(ElasticSearchUpdater.class);

    @Autowired
    public ElasticSearchUpdater(RabbitMQReceiver rabbitMQReceiver, ElasticSearchService elasticSearchService, ObjectMapper objectMapper) throws Exception {
        this.elasticSearchService = elasticSearchService;
        this.objectMapper = objectMapper;

        rabbitMQReceiver.receive(PLANE_TYPE_INSERTED_QUEUE, this::updatePlaneType);
    }

    private void updatePlaneType(String consumerTag, Delivery message) throws IOException {
        PlaneType planeType = objectMapper.readValue(message.getBody(), PlaneType.class);

        elasticSearchService.<PlaneType>insertDocument("plane-type", planeType.getId().toString(), planeType);
    }
}
