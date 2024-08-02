package com.urunsiyabend.flightscqrs.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaneTypeRequest {
    private String manufacturer;
    private String model;
    private Integer capacity;
}
