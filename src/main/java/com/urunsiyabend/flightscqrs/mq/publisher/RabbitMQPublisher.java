package com.urunsiyabend.flightscqrs.mq.publisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface RabbitMQPublisher {
    void publish(String queueName, String data) throws IOException, TimeoutException;
}