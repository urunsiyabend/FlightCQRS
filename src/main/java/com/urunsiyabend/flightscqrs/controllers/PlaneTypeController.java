package com.urunsiyabend.flightscqrs.controllers;

import com.urunsiyabend.flightscqrs.controllers.requests.CreatePlaneTypeRequest;
import com.urunsiyabend.flightscqrs.controllers.responses.CreatePlaneTypeResponse;
import com.urunsiyabend.flightscqrs.controllers.responses.GetPlaneTypeResponse;
import com.urunsiyabend.flightscqrs.services.PlaneTypeService;
import com.urunsiyabend.flightscqrs.planetype.PlaneTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/plane-types")
public class PlaneTypeController {
    private final PlaneTypeService planeTypeService;

    @Autowired
    public PlaneTypeController(PlaneTypeService planeTypeService) {
        this.planeTypeService = planeTypeService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<GetPlaneTypeResponse>> getPlaneTypes() throws Exception {
        Collection<PlaneTypeDTO> planeTypes = planeTypeService.getPlaneTypes();

        return ResponseEntity.ok(
                planeTypes.stream()
                        .map(planeType -> GetPlaneTypeResponse.builder()
                                .id(String.valueOf(planeType.getId()))
                                .manufacturer(planeType.getManufacturer())
                                .model(planeType.getModel())
                                .capacity(planeType.getCapacity())
                                .build())
                        .toList()
        );
    }

    @PostMapping("")
    public ResponseEntity<CreatePlaneTypeResponse> createPlaneType(@RequestBody CreatePlaneTypeRequest createPlaneTypeRequest) throws Exception {
        PlaneTypeDTO planeTypeDTO = PlaneTypeDTO.builder()
                .manufacturer(createPlaneTypeRequest.getManufacturer())
                .model(createPlaneTypeRequest.getModel())
                .capacity(createPlaneTypeRequest.getCapacity())
                .build();

        PlaneTypeDTO createdPlaneType = planeTypeService.createPlaneType(planeTypeDTO);

        return ResponseEntity.ok(CreatePlaneTypeResponse.builder()
                .id(createdPlaneType.getId())
                .manufacturer(createdPlaneType.getManufacturer())
                .model(createdPlaneType.getModel())
                .capacity(createdPlaneType.getCapacity())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaneType(@PathVariable String id) throws Exception {
        id = id.trim();
        Long idLong = Long.parseLong(id);
        planeTypeService.deletePlaneType(idLong);

        return ResponseEntity.noContent().build();
    }
}
