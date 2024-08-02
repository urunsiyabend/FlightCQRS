package com.urunsiyabend.flightscqrs.mq.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMQServerImpl implements RabbitMQServer {
    private final ConnectionFactory connectionFactory;

    public RabbitMQServerImpl(@Value("${rabbitmq.host}") String RABBITMQ_HOST,
                              @Value("${rabbitmq.port}") Integer RABBITMQ_PORT,
                              @Value("${rabbitmq.username}") String RABBITMQ_USERNAME,
                              @Value("${rabbitmq.password}") String RABBITMQ_PASSWORD) {
        this.connectionFactory = new ConnectionFactory();

        connectionFactory.setHost(RABBITMQ_HOST);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
        connectionFactory.setPort(RABBITMQ_PORT);
    }

    @Override
    public Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    @Override
    public Channel getChannel(Connection connection) throws IOException {
        return connection.createChannel();
    }
}