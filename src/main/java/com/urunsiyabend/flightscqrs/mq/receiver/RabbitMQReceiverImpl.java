package com.urunsiyabend.flightscqrs.mq.receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.urunsiyabend.flightscqrs.mq.server.RabbitMQServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMQReceiverImpl implements RabbitMQReceiver {
    private final RabbitMQServer rabbitMQServer;

    @Autowired
    public RabbitMQReceiverImpl(RabbitMQServer rabbitMQServer) {
        this.rabbitMQServer = rabbitMQServer;
    }

    @Override
    public void receive(String queueName, DeliverCallback deliverCallback) throws IOException, TimeoutException {
        Connection connection = rabbitMQServer.getConnection();
        Channel channel = rabbitMQServer.getChannel(connection);

        channel.queueDeclare(queueName, true, false, false, null);

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
