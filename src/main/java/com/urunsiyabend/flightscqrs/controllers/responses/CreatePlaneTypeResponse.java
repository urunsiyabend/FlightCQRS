package com.urunsiyabend.flightscqrs.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaneTypeResponse {
    private Long id;
    private String manufacturer;
    private String model;
    private Integer capacity;
}
