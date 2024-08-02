package com.urunsiyabend.flightscqrs.mq.receiver;

import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface RabbitMQReceiver {
    void receive(String queueName, DeliverCallback deliverCallback) throws IOException, TimeoutException;
}
