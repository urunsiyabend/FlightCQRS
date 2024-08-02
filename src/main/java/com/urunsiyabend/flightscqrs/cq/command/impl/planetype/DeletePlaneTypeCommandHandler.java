package com.urunsiyabend.flightscqrs.cq.command.impl.planetype;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urunsiyabend.flightscqrs.cq.command.common.CommandHandler;
import com.urunsiyabend.flightscqrs.cq.command.common.CommandResult;
import com.urunsiyabend.flightscqrs.mq.publisher.RabbitMQPublisher;
import com.urunsiyabend.flightscqrs.repositories.PlaneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class DeletePlaneTypeCommandHandler implements CommandHandler<DeletePlaneTypeCommand> {
    private final PlaneTypeRepository planeTypeRepository;
    private final RabbitMQPublisher rabbitMQPublisher;
    private final ObjectMapper objectMapper;

    private final String PLANE_TYPE_DELETED_QUEUE = "PLANE_TYPE_DELETED_QUEUE";

    @Autowired
    public DeletePlaneTypeCommandHandler(PlaneTypeRepository planeTypeRepository, RabbitMQPublisher rabbitMQPublisher, ObjectMapper objectMapper) {
        this.planeTypeRepository = planeTypeRepository;
        this.rabbitMQPublisher = rabbitMQPublisher;
        this.objectMapper = objectMapper;
    }

    @Override
    public CommandResult handle(DeletePlaneTypeCommand command) throws IOException, TimeoutException {
        planeTypeRepository.deleteById(command.getId());

        rabbitMQPublisher.publish(PLANE_TYPE_DELETED_QUEUE, command.getId().toString());

        return CommandResult.success();
    }
}
