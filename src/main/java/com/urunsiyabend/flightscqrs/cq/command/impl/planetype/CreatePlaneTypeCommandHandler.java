package com.urunsiyabend.flightscqrs.cq.command.impl.planetype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urunsiyabend.flightscqrs.cq.command.common.CommandHandler;
import com.urunsiyabend.flightscqrs.cq.command.common.CommandResult;
import com.urunsiyabend.flightscqrs.entities.PlaneType;
import com.urunsiyabend.flightscqrs.mq.publisher.RabbitMQPublisher;
import com.urunsiyabend.flightscqrs.repositories.PlaneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class CreatePlaneTypeCommandHandler implements CommandHandler<CreatePlaneTypeCommand> {
    private final PlaneTypeRepository planeTypeRepository;
    private final RabbitMQPublisher rabbitMQPublisher;
    private final ObjectMapper objectMapper;
    Logger logger = LoggerFactory.getLogger(CreatePlaneTypeCommandHandler.class);

    private final String PLANE_TYPE_INSERTED_QUEUE = "PLANE_TYPE_INSERTED_QUEUE";

    @Autowired
    public CreatePlaneTypeCommandHandler(PlaneTypeRepository planeTypeRepository, RabbitMQPublisher rabbitMQPublisher, ObjectMapper objectMapper) {
        this.planeTypeRepository = planeTypeRepository;
        this.rabbitMQPublisher = rabbitMQPublisher;
        this.objectMapper = objectMapper;
    }

    @Override
    public CommandResult handle(CreatePlaneTypeCommand command) throws IOException, TimeoutException {
        PlaneType planeType = PlaneType.builder()
                .manufacturer(command.getManufacturer())
                .model(command.getModel())
                .capacity(command.getCapacity())
                .build();

        PlaneType insertedPlaneType = planeTypeRepository.save(planeType);

        rabbitMQPublisher.publish(PLANE_TYPE_INSERTED_QUEUE, objectMapper.writeValueAsString(insertedPlaneType));

        return CommandResult.success();
    }
}
