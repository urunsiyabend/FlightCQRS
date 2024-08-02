package com.urunsiyabend.flightscqrs.services;

import com.urunsiyabend.flightscqrs.cq.command.impl.planetype.CreatePlaneTypeCommand;
import com.urunsiyabend.flightscqrs.cq.command.impl.planetype.CreatePlaneTypeCommandHandler;
import com.urunsiyabend.flightscqrs.cq.command.impl.planetype.DeletePlaneTypeCommand;
import com.urunsiyabend.flightscqrs.cq.command.impl.planetype.DeletePlaneTypeCommandHandler;
import com.urunsiyabend.flightscqrs.cq.query.impl.planetype.GetPlaneTypesQuery;
import com.urunsiyabend.flightscqrs.cq.query.impl.planetype.GetPlaneTypesQueryHandler;
import com.urunsiyabend.flightscqrs.entities.PlaneType;
import com.urunsiyabend.flightscqrs.planetype.PlaneTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlaneTypeService {
    private final GetPlaneTypesQueryHandler getPlaneTypesQueryHandler;
    private final CreatePlaneTypeCommandHandler createPlaneTypeCommandHandler;

    private final DeletePlaneTypeCommandHandler deletePlaneTypeCommandHandler;

    @Autowired
    public PlaneTypeService(
            GetPlaneTypesQueryHandler getPlaneTypesQueryHandler,
            CreatePlaneTypeCommandHandler createPlaneTypeCommandHandler,
            DeletePlaneTypeCommandHandler deletePlaneTypeCommandHandler
    ) {
        this.getPlaneTypesQueryHandler = getPlaneTypesQueryHandler;
        this.createPlaneTypeCommandHandler = createPlaneTypeCommandHandler;
        this.deletePlaneTypeCommandHandler = deletePlaneTypeCommandHandler;
    }

    public Collection<PlaneTypeDTO> getPlaneTypes() throws Exception {
        Collection<PlaneType> planeTypes = getPlaneTypesQueryHandler.handle(GetPlaneTypesQuery.builder().build());
        return planeTypes.stream()
                .map(planeType -> PlaneTypeDTO.builder()
                        .id(planeType.getId())
                        .manufacturer(planeType.getManufacturer())
                        .model(planeType.getModel())
                        .capacity(planeType.getCapacity())
                        .build())
                .toList();
    }

    public PlaneTypeDTO createPlaneType(PlaneTypeDTO planeTypeDTO) throws Exception {
        var command = CreatePlaneTypeCommand.builder()
                .manufacturer(planeTypeDTO.getManufacturer())
                .model(planeTypeDTO.getModel())
                .capacity(planeTypeDTO.getCapacity())
                .build();

        createPlaneTypeCommandHandler.handle(command);

        return planeTypeDTO;
    }

    public void deletePlaneType(Long id) throws Exception {
        var command = DeletePlaneTypeCommand.builder()
                .id(id)
                .build();

        deletePlaneTypeCommandHandler.handle(command);
    }
}
