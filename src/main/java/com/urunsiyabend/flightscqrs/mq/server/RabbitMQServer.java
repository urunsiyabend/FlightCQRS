package com.urunsiyabend.flightscqrs.mq.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface RabbitMQServer {
    Connection getConnection() throws IOException, TimeoutException;
    Channel getChannel(Connection connection) throws IOException;
}