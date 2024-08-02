package com.urunsiyabend.flightscqrs.mq.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.urunsiyabend.flightscqrs.mq.server.RabbitMQServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMQPublisherImpl implements RabbitMQPublisher {
    private final RabbitMQServer rabbitMQServer;

    @Autowired
    public RabbitMQPublisherImpl(RabbitMQServer rabbitMQServer) {
        this.rabbitMQServer = rabbitMQServer;
    }

    @Override
    public void publish(String queueName, String data) throws IOException, TimeoutException {
        try (Connection connection = rabbitMQServer.getConnection()) {
            try (Channel channel = rabbitMQServer.getChannel(connection)) {
                channel.queueDeclare(queueName, true, false, false, null);
                channel.basicPublish("", queueName, null, data.getBytes());
            }
        }
    }
}
